package com.lge.ex1;

// https://github.com/imguru/JUnit_200707.git

// 1. JDK 8
// https://www.oracle.com/kr/java/technologies/javase/javase-jdk8-downloads.html
// http://bugmenot.com/view/oracle.com

// 2. IntelliJ Community
// https://www.jetbrains.com/ko-kr/idea/download/

// JUnit 3 에서 Test Suite(Test Case) 를 만드는 방법.
// public class CalculatorTest extends TestCase

import org.junit.Test;

// import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

// 1) 단위 테스트를 구성하는 가장 일반적인 방법
//  => Testcase class per class
//  : 하나의 SUT에 대한 테스트 메소드를 하나의 테스트 케이스 클래스 안에
//    전부 집어넣는다.

// 2) 테스트 메소드를 구성하는 방법 - 3A
//   1. Arrange(Given): 객체를 생성하고, 적절하게 설정한다.
//   2. Act(When): 객체에 작용을 가한다.
//   3. Assert(Then): 기대하는 바를 검증(단언)하다.

// TDD(테스트 주도 개발) -> BDD(행위 주도 개발)
//                      - 용어
//                      - 행위 검증

// 3) 테스트 코드의 품질
//  - '커버리지'를 통해 테스트 코드의 품질을 측정할 수 없다.
//  => Effective Unit Testing
//  1) 가독성
//  2) 유지보수성
//  3) 신뢰성

public class CalculatorTest {

    @Test
    public void testSub() throws Exception {
        // Given
        Calculator calculator = new Calculator();
        // When
        calculator.sub(2);
        // Then
        assertEquals(-2, calculator.getValue());
    }

    @Test
    public void testFoo() throws Exception {
        Calculator calculator = new Calculator();

        calculator.add(2);
        calculator.sub(2);

        int result = calculator.getValue();
    }

    // JUnit 3에서 테스트 메소드는 메소드의 이름이 test로 시작해야 합니다.
    // JUnit 4에서 테스트 메소드를 만드는 방법.
    // => Annotation - AOP

    // 개선점
    // 1) 테스트의 '가독성'을 위해서는,
    //    - 테스트의 이름에서 어떤 테스트가 수행되고 있는지를 알 수 있어야 한다.
    //      ex) 테스트대상메소드_시나리오_기대값
    //    - 테스트가 실패할 경우, 실패의 원인이 무엇인지 알 수 있어야 한다.

    // 2) 단위 테스트의 코드는 간결해야 한다.
    //   => 유지보수의 비용이 최소화되어야 한다.
    //     : 테스트 메소드 안에서 제어 구문(조건문, 반복문, 예외처리)의 발생을 최소화해야 한다.
    @Test
    public void add_2더하기2_결과는4() throws Exception {
        // public void add_addingTwoPlusTwo_getValue_ReturnFour() throws Exception {
        // public void testAdd() throws Exception {
        Calculator calculator = new Calculator();

        calculator.add(2);
        calculator.add(2);

        /*
        if (calculator.getValue() != 4) {
            throw new Exception("2+2 하였을 때 4가 나오지 않았음");
        }
        */
        // xUnit Test Framework은 가정이 성립되었는지 여부를 검증하는 목적으로
        // Assertion 메소드를 제공해주고 있습니다.
        assertEquals("2 + 2 하였을 때", 4, calculator.getValue());
    }

}





