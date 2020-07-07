package com.lge.ex5;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


// Parameterized Test Pattern
// : xUnit Test Framework가 지원하는 기능입니다.
// 정의: 입력 데이터를 바꿔가면서, 수차례 반복 검사하는 데이터 중심의 테스트에서 코드 중복을 없애주는 기법.

// * 파라미터화 테스트를 작성하는 방법.
// 1) TestRunner가 다릅니다.
//   TestSuite - @RunWith(Parameterized.class)

// 2) 데이터에 대한 정의가 필요합니다.
//   static method - @Parameterized.Parameters

// JUnit4 에서 TestSuite객체 생성할 때, Reflection의 기술을 이용합니다.
// Reflection에서는 생성자의 인자를 Object[] 형태로 전달하고 있습니다.
/*
    ParamTest ts = new ParamTest({2});
    ts.primeTest();

    ParamTest ts = new ParamTest({3});
    ts.primeTest();

    ParamTest ts = new ParamTest({5});
    ts.primeTest();

    ...
*/
// 3) 생성자와 필드를 정의해서, runner에 의해서 전달되는 인자를 저장한다.
// 4) testcase에서 필드를 통해 테스트를 수행하면 됩니다.

// 주의점
// 1) 복잡하다.
//   - xUnit Test Framework 마다 제공하는 방법이 다르다.
// 2) 테스트가 실패한 경우, 데이터가 무엇인지 알기 어렵다.
//   - @Parameterized.Parameters(name = "{index}, value={0}")
// 3) 파라미터화 테스트를 위해서는 별도의 Test Suite 클래스를 작성해야 한다.


@RunWith(Parameterized.class)
public class ParamTest {
    private int value;
    private boolean result;

    public ParamTest(int value, boolean result) {
        this.value = value;
        this.result = result;
    }

    @Parameterized.Parameters(name = "{index}, value={0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {2, true},
                {3, true},
                {5, true},
                {7, true},
                {11, true},
                {13, true},
                {17, true},
                {19, true},
                {10, false},
                {15, false},
        });
    }

    @Test
    public void primeTest1() throws Exception {
        // assertTrue(Utils.isPrime(value));
        assertEquals(result, Utils.isPrime(value));
    }
}


/*
public class ParamTest {
    @Test
    public void primeTest1() throws Exception {
        int[] data = {2, 3, 5, 7, 11, 13, 17};

        for (int i = 0; i < data.length; ++i) {
            assertTrue(Utils.isPrime(data[i]));
        }
    }

    // 단언 메소드를 사용할 때 주의 사항
    //  : 단언 메소드가 실패할 경우, 이후의 코드는 수행되지 않는다.
    //  => 테스트 메소드 안에서 정적한 개수의 단언 메소드를 사용하는 것이 좋다.
    @Test
    public void primeTest2() throws Exception {
        assertTrue(Utils.isPrime(2));
        assertTrue(Utils.isPrime(3));
        assertTrue(Utils.isPrime(5));
        assertTrue(Utils.isPrime(7));
        assertTrue(Utils.isPrime(11));
        assertTrue(Utils.isPrime(13));
        assertTrue(Utils.isPrime(17));
    }
}
*/

class Utils {
    public static boolean isPrime(int value) {
        for (int i = 2; i < value; ++i) {
            if (value % i == 0)
                return false;
        }

        return true;
    }
}

