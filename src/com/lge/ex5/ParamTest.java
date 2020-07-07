package com.lge.ex5;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


// Parameterized Test Pattern
// : xUnit Test Framework가 지원하는 기능입니다.
// 정의: 입력 데이터를 바꿔가면서, 수차례 반복 검사하는 데이터 중심의 테스트에서 코드 중복을 없애주는 기법.

// * 파라미터화 테스트를 작성하는 방법.


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

