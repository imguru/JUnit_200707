package com.lge.ex9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

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