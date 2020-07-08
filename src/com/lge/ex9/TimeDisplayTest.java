package com.lge.ex9;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeDisplayTest {
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

        int h = time.get(Calendar.HOUR);
        int m = time.get(Calendar.MINUTE);
        String str = String.format("%2d:%02d", h, m);
        if (str.equals("00:00")) {
            str = "Midnight";
        }

        return str;
    }
}