package com.lge.ex12;

// Mock Object Pattern
// 의도: SUT의 메소드를 호출하였을 때, 발생하는 부수효과를 관찰 할 수 없어, 테스트 안된 요구사항이 있다.
// 방법: '행위 기반 검증'을 통해 검증을 수행한다.

// 1. 상태 검증(State Verification)
//  : 테스트에 검증할 수 있는 부수 효과가 존재한다.

// 2. 행위 검증(Behavior Verification)
//  : 올바른 로직 수행의 판단의 근거로, 메소드의 수행 여부를 이용하는 것
//   1) 메소드의 호출 여부
//   2) 메소드의 호출 횟수
//   3) 메소드의 호출 순서

// 모의 객체
//  : 메소드의 호출에 대한 정보를 기록하는 객체

// 모의 객체를 생성하기 위해서는 'Mock Framework'을 이용하면 됩니다.
//  Java: jMock, EasyMock, Mockito
//  C++: Google Mock


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

// Mockito를 이용하면, mock을 '동적'으로 생성할 수 있습니다.

public class DLoggerTest {
    @Test
    public void writeTest2() {
        DLoggerTarget mock1 = mock(DLoggerTarget.class);
        DLoggerTarget mock2 = mock(DLoggerTarget.class);
        DLogger logger = new DLogger(mock1, mock2);

        logger.write(Level.INFO, "test_message_info");
        logger.write(Level.CRITICAL, "test_message_critical");

        assertAll(
                () -> verify(mock1).write(Level.INFO, "test_message_info"),
                () -> verify(mock2).write(Level.INFO, "test_message_info"),
                () -> verify(mock1).write(Level.CRITICAL, "test_message_critical"),
                () -> verify(mock2).write(Level.CRITICAL, "test_message_critical")
        );
    }

    @Test
    public void writeTest() {
        DLoggerTarget mock1 = mock(DLoggerTarget.class);
        DLoggerTarget mock2 = mock(DLoggerTarget.class);
        DLogger logger = new DLogger(mock1, mock2);

        logger.write(Level.INFO, "test_message_info");
        logger.write(Level.CRITICAL, "test_message_critical");

        verify(mock1).write(Level.INFO, "test_message_info");
        verify(mock2).write(Level.INFO, "test_message_info");
        verify(mock1).write(Level.CRITICAL, "test_message_critical");
        verify(mock2).write(Level.CRITICAL, "test_message_critical");
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