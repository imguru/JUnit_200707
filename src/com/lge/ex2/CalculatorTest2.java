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
import static org.junit.jupiter.api.Assumptions.*;

// @Before, @After          -> @BeforeEach, @AfterEach
// @BeforeClass @AfterClass -> @BeforeAll, @AfterAll
// @Ignore                  -> @Disabled

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest2 {
    public CalculatorTest2() {
        System.out.println("CalculatorTest()");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("setUp()");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("tearDown()");
    }

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

    // @Disabled
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

    // Google Test
    //  ASSERT_EQ가 실패할 경우, 이후의 코드가 수행되지 않는다.
    //  EXPECT_ 계열의 단언 매크로를 사용하면, 이후의 코드는 수행된다.
    // => 죽은 단언문의 문제를 해결할 수 있다.

    // JUnit 5
    //  : assertAll
    //  - 여러개의 단언 메소드를 수행하였을 때, '죽은 단언문'의 문제를 해결할 수 있다.

    @RepeatedTest(42)
    public void sampleTest() throws Exception {

        assertAll("Numbers",
                () -> assertEquals(20, 20, "reason 1"),
                () -> assertEquals(20, 20, "reason 2"),
                () -> assertEquals(20, 20, "reason 3")
                );

//        assertEquals(10, 20, "reason 1");
//        assertEquals(10, 20, "reason 2");
//        assertEquals(10, 20, "reason 3");
    }

    // 전제조건이 성립되지 않으면, 테스트를 수행하지 않아야 한다.
    // => Assumptions
    //   : 조건이 성립되지 않으면, 테스트는 수행되지 않는다.
    //   JUnit의 고유의 기능입니다.

    @Test
    public void fooTest() {
        // assumeTrue(5 < 1);

        assertEquals(40, 10 + 20, "Reason foo");
    }


}
