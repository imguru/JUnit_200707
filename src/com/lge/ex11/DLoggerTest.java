package com.lge.ex11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Test Spy Pattern
// 의도: SUT의 메소드를 호출하였을 때, 발생하는 부수효과를 관찰 할 수 없어, 테스트 안된 요구사항이 있다.
// 방법: 목격한 일을 기록해두었다가, 나중에 테스트에서 확인할 수 있도록 만들어진 테스트 대역

class SpyTarget implements DLoggerTarget {
    private List<String> history = new ArrayList<>();

    private String concat(Level level, String message) {
        return level.name() + "@" + message;
    }

    @Override
    public void write(Level level, String message) {
        history.add(concat(level, message));
    }

    public boolean isReceived(Level level, String message) {
        return history.contains(concat(level, message));
    }
}

public class DLoggerTest {
    @DisplayName("DLogger에 여러개의 타겟을 등록하고, write를 수행하였을 때, 제대로 처리되었는지 여부를 검증하고 싶다.")
    @Test
    public void writeTest2() {
        SpyTarget spy1 = new SpyTarget();
        SpyTarget spy2 = new SpyTarget();
        DLogger logger = new DLogger(spy1, spy2);

        logger.write(Level.INFO, "test_message_info");
        logger.write(Level.CRITICAL, "test_message_critical");

        assertAll(
                () -> assertTrue(spy1.isReceived(Level.INFO, "test_message_info"), "spy1 - info"),
                () -> assertTrue(spy2.isReceived(Level.INFO, "test_message_info")),
                () -> assertTrue(spy1.isReceived(Level.CRITICAL, "test_message_critical")),
                () -> assertTrue(spy2.isReceived(Level.CRITICAL, "test_message_critical"))
        );
    }


    @DisplayName("DLogger에 여러개의 타겟을 등록하고, write를 수행하였을 때, 제대로 처리되었는지 여부를 검증하고 싶다.")
    @Test
    public void writeTest() {
        SpyTarget spy1 = new SpyTarget();
        SpyTarget spy2 = new SpyTarget();
        DLogger logger = new DLogger(spy1, spy2);

        logger.write(Level.INFO, "test_message_info");
        logger.write(Level.CRITICAL, "test_message_critical");

        assertTrue(spy1.isReceived(Level.INFO, "test_message_info"));
        assertTrue(spy2.isReceived(Level.INFO, "test_message_info"));
        assertTrue(spy1.isReceived(Level.CRITICAL, "test_message_critical"));
        assertTrue(spy2.isReceived(Level.CRITICAL, "test_message_critical"));
    }
}

//-----------------------
enum Level {
    INFO, DEBUG, ERROR, CRITICAL
}

interface DLoggerTarget {
    void write(Level level, String message);
}

class DLogger {
    private DLoggerTarget[] targets;

    public DLogger(DLoggerTarget... targets) {
        this.targets = targets;
    }

    public void write(Level level, String message) {
        for (DLoggerTarget e : targets) {
            // e.write(level, message);
        }
    }
}