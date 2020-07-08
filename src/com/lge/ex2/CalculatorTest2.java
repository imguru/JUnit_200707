package com.lge.ex2;

// JUnit 5 - Java 1.8 이상부터 사용할 수 있습니다.
// 3가지 구성 요소
// 1. Platform
// 2. Jupiter - JUnit5 API
// 3. Vintage - JUnit 3/4

import com.lge.ex1.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

// @Before, @After          -> @BeforeEach, @AfterEach
// @BeforeClass @AfterClass -> @BeforeAll, @AfterAll
// @Ignore                  -> @Disabled

@Execution(ExecutionMode.CONCURRENT)
public class CalculatorTest2 {
//    public CalculatorTest2() {
//        System.out.println("CalculatorTest()");
//    }

//    @BeforeEach
//    public void setUp() {
//        System.out.println("setUp()");
//    }
//
//    @AfterEach
//    public void tearDown() {
//        System.out.println("tearDonw()");
//    }

    @BeforeAll
    public static void setUpTestSuite() {
        System.out.println("setUpTestSuite()");
    }

    @AfterAll
    public static void tearDownTestSuite() {
        System.out.println("tearDownTestSuite()");
    }

    @DisplayName("calc 객체에 대해서 2 + 2를 수행하였을 때, 4가 나오지는 검증")
    @Test
    public void addTest() {
        System.out.println("addTest()");
        Calculator calculator = new Calculator();

        calculator.add(2);
        calculator.add(2);

        assertEquals(4, calculator.getValue(), "2 + 2 했을 때");
    }

    @Disabled
    @Test
    public void subTest() {
        System.out.println("subTest()");
    }

    // @Test(expected=NumberFormatException.class)
    // => assertThrows
    @Test
    public void parseIntTest() {
        String badNumberStr = "16";

//        assertThrows(NumberFormatException.class, () -> {
//            Integer.parseInt(badNumberStr);
//        });
        assertThrows(NumberFormatException.class, () -> Integer.parseInt(badNumberStr));
    }

    public static void slowJob() throws Exception {
        TimeUnit.MICROSECONDS.sleep(150);
    }

    @Test
    public void timeoutTest() throws Exception {
        assertTimeout(Duration.ofNanos(200 * 1000), () -> slowJob());
    }

    // JUnit 5는 파라미터화 테스트를 별도의 TestSuite 클래스에서 작성할 필요가 없습니다.
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17})
    public void isPrimeTest(int primeValue) throws Exception {
        System.out.println(primeValue);
        assertTrue(Calculator.isPrime(primeValue));
    }

    @ParameterizedTest
    @MethodSource("getPrimeValues")
    public void isPrimeTest2(int primeValue, boolean result) throws Exception {
        System.out.println(primeValue);
        assertTrue(Calculator.isPrime(primeValue));
    }

    private static Stream<Arguments> getPrimeValues() {
        return Stream.of(
                Arguments.of(2, true),
                Arguments.of(3, true),
                Arguments.of(5, true),
                Arguments.of(7, true),
                Arguments.of(11, true),
                Arguments.of(13, true),
                Arguments.of(17, true));
    }


}
