package com.lge.ex2;

import com.lge.ex1.Calculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

// Test Fixture
//  정의: 테스트를 실행하는 데 있어, 준비해야하는 모든 작업
//   => 객체 생성, 객체에 적절한 설정, 사전 조건 설정
//       테스트 픽스쳐에 관한 코드를 "테스트 픽스쳐를 설치 한다"라고 합니다.

// Test Fixture를 설치하는 방법
// 1. Inline Setup
//   정의: 모든 픽스쳐 설치를 테스트 메소드 안에서 수행한다.
//   장점: 테스트의 인과 관계를 쉽게 파악할 수 있다.
//   단점: 1) 테스트 코드 중복이 발생한다.
//        2) 픽스쳐 설치 과정이 복잡하면, 테스트 메소드를 이해하기 어렵게 만든다.

// 2. Delegate Setup(위임 설치)
//  정의: 픽스쳐 설치에 관한 코드를 테스트 유틸리티 메소드를 통해 캡슐화한다.

// 3. Implicit Set up/Tear down(암묵적 설치/해체)
//  : xUnit Test Framework가 지원하는 기능.
//  xUnit Test Framework이 제공하는 메소드를 통해 테스트 픽스쳐를 암묵적으로 설치한다.
//    setUp() - JUnit3
//  장점: 테스트 코드 중복을 제거하고, 불필요한 상호작용을 감출 수 있다.
//  단점: 테스트 메소드만으로 인과 관계를 분석하기 어려울 수 있다.

// 4. 복합 설치
//    : 암묵적 설치와 위임 설치를 같이 적절하게 혼합해서 사용하는 방법

// * 테스트 러너(Test Runner) - '신선한 픽스쳐 전략'
// CalculatorTest ts = new CalculatorTest();
// ts.setUp();  - 비메모리 자원을 생성한다. / 명시적인 종료 메소드를 통해 자원을 해지해주어야 한다.
//                테스트가 종료될 때, 테스트 픽스쳐를 해체해서, 테스트 시작 이전의 상태로 복원해야 한다.
// ts.testSub();
// ts.tearDown();

// CalculatorTest ts = new CalculatorTest();
// ts.setUp();
// ts.testAdd();
// ts.tearDown();

// * xUnit Test Pattern에서 테스트를 구성하는 방법
// : Four Phase Test Pattern(4단계 테스트 패턴)
// 1단계: 픽스쳐를 설치하거나, 관찰하기 위해 필요한 것을 설정하는 작업   - @Before
// 2단계: SUT와 상호작용한다.
// 3단계: 기대 결과가 나왔는지 확인한다.
// 4단계: 픽스쳐를 해체해서, 테스트 이전 상태로 되돌려 놓는다.        - @After


public class CalculatorTest {
    public CalculatorTest() {
        System.out.println("CalculatorTest()");
    }

    private Calculator calculator;

    @Before
    public void setUp() throws Exception {
        System.out.println("setUp()");
        calculator = new Calculator();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("자원 해체");
    }

    @Test
    public void testSub() throws Exception {
        System.out.println("testSub()");
        // When
        calculator.sub(2);
        // Then
        assertEquals(0, calculator.getValue());
        // : 단언 메소드가 실패할 경우, 이후의 코드가 수행되지 않는다.
        //   테스트 픽스쳐를 해체하는 작업을, 테스트 메소드에 직접 작성하는 것은 누수의 위험성이 존재한다.

        // System.out.println("자원 해체");
    }

    @Test
    public void testAdd() throws Exception {
        System.out.println("testAdd()");
        calculator.add(2);
        calculator.add(2);

        assertEquals("2 + 2 하였을 때", 4, calculator.getValue());
    }
}


/*
public class CalculatorTest {
    // Test Utility Method: Creation Method
    private Calculator createCalculator() {
        return new Calculator();
    }

    @Test
    public void testSub() throws Exception {
        // Given
        Calculator calculator = createCalculator();
        // When
        calculator.sub(2);
        // Then
        assertEquals(-2, calculator.getValue());
    }

    @Test
    public void testAdd() throws Exception {
        Calculator calculator = createCalculator();

        calculator.add(2);
        calculator.add(2);

        assertEquals("2 + 2 하였을 때", 4, calculator.getValue());
    }
}
*/


/*
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
    public void testAdd() throws Exception {
        Calculator calculator = new Calculator();

        calculator.add(2);
        calculator.add(2);

        assertEquals("2 + 2 하였을 때", 4, calculator.getValue());
    }
}
*/
