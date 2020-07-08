package com.lge.ex9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StubTimeProvider implements TimeProvider {
    @Override
    public Calendar getTime() {
        Calendar time = new GregorianCalendar();

        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);

        return time;
    }
}

public class TimeDisplayTest {
    @DisplayName("자정에 Midnight의 문자열이 제대로 발생하는지 여부를 검증하고 싶다.")
    @Test
    public void getCurrentTimeAsStringTest() {
        StubTimeProvider stub = new StubTimeProvider();
        TimeDisplay display = new TimeDisplay(stub);

        assertEquals("Midnight", display.getCurrentTimeAsString());
    }

    @DisplayName("자정에 Midnight의 문자열이 제대로 발생하는지 여부를 검증하고 싶다.")
    @Test
    public void getCurrentTimeAsStringTest_mockito() {
        Calendar midTime = new GregorianCalendar();
        midTime.set(Calendar.HOUR_OF_DAY, 0);
        midTime.set(Calendar.MINUTE, 0);
        TimeProvider stub = mock(TimeProvider.class);
        when(stub.getTime()).thenReturn(midTime);
        TimeDisplay display = new TimeDisplay(stub);

        assertEquals("Midnight", display.getCurrentTimeAsString());
    }
}

interface TimeProvider {
    Calendar getTime();
}

class MyTimeProvider implements TimeProvider {
    @Override
    public Calendar getTime() {
        return GregorianCalendar.getInstance();
    }
}

class TimeDisplay {
    private TimeProvider provider;

    public TimeDisplay() {
        // provider = new MyTimeProvider();
        this(new MyTimeProvider());
    }

    public TimeDisplay(TimeProvider provider) {
        this.provider = provider;
    }

    public String getCurrentTimeAsString() {
        Calendar time = provider.getTime();

        int h = time.get(Calendar.HOUR_OF_DAY);
        int m = time.get(Calendar.MINUTE);
        String str = String.format("%02d:%02d", h, m);
        if (str.equals("00:00")) {
            str = "Midnight";
        }

        return str;
    }
}

// 테스트 대역 종류
// 1. Stub
//    : 특수한 상황을 시뮬레이션 한다.
// 2. Fake
//    : 아직 준비되지 않은 컴포넌트에 의지 하는 SUT를 검증하기 위해 사용한다.
//  - SUT의 메소드를 호출하였을 때, 관찰할 수 있는 부수효과가 존재하지 않아, 테스트 되지 않은 요구사항이
//    존재한다.
// 3. Spy
//   : 숨겨진 정보를 확인할 수 있도록, 테스트 대역
// 4. Mock
//   : 행위 기반 검증

















