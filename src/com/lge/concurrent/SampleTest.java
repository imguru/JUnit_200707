package com.lge;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


@Execution(ExecutionMode.CONCURRENT)
public class SampleTest {

    public static void slowJob() throws Exception {
        // TimeUnit.NANOSECONDS.sleep(1000 * 30);
    }

    @Timeout(value = 40 * 1000 * 1000, unit = TimeUnit.NANOSECONDS)
    @Test
    public void timeoutTest() throws Exception {
        // slowJob();
        // assertTimeout(Duration.ofNanos(1000 * 50), () -> slowJob());
    }


    @ParameterizedTest
    @ValueSource(ints = {
            2, 3, 5, 7, 11, 13, 17,
            2, 3, 5, 7, 11, 13, 17,
            2, 3, 5, 7, 11, 13, 17,
            2, 3, 5, 7, 11, 13, 17,
            2, 3, 5, 7, 11, 13, 17,
    })
    public void fooTest(int value) throws Exception {
        String s = "";
        for (int i = 0; i < 100000; ++i)
            s += i;

        System.out.println(value);
    }
}
