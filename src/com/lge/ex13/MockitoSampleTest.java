package com.lge.ex13;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

public class MockitoSampleTest {
    // 행위 기반 검증
    // 1) 호출 여부

    private SUT sut;

    @Mock
    private List<String> mock;

    @Mock
    private List<String> mock2;

    @BeforeEach
    public void setUp() {
        sut = new SUT();

        MockitoAnnotations.initMocks(this);
        // mock = mock(List.class);
        // mock = createMock();
    }

    @SuppressWarnings("unchecked")
    private List<String> createMock() {
        return mock(List.class);
    }

    @Test
    public void fooTest() {
        // Given
        // SUT sut = new SUT();
        // List<String> mock = mock(List.class);

        // When
        sut.foo(mock);

        // Then
        verify(mock).add("one");
        verify(mock).add("two");
    }

    // 2) 호출 횟수
    // : 호출 횟수를 verify에서 명시하지 않으면, 한번의 호출을 체크한다.
    // - times(N): 정확한 N번
    // - atLeast(N): 적어도 N번 / atLeastOnce == atLeast(1)
    // - atMost(N): 최대 N번   /  atMostOnce == atMost(1)

    // - 호출 인자에 대한 부분에 대해서 주의해야 합니다.
    //   : Matcher에 대한 정책에 따라서, 호출 횟수가 다르게 카운트 됩니다.

    @Test
    public void gooTest2() {
//        SUT sut = new SUT();
//        List<String> mock = mock(List.class);

        sut.goo(mock);

        verify(mock, times(3)).add(anyString());
    }

    @Test
    public void gooTest() {
//        SUT sut = new SUT();
//        List<String> mock = mock(List.class);

        sut.goo(mock);

        verify(mock).add("once");
        // verify(mock, times(2)).add("twice");
        verify(mock, atLeast(2)).add("twice");
    }


//    @Test
//    public void hooTest() {
//        // mock 함수에는 모든 타입에 대해서 사용하는 것이 가능하다.
//        Point mock = mock(Point.class);
//        SUT sut = new SUT();
//
//        sut.hoo(mock);
//
//        verify(mock, times(3)).move(eq(10), anyInt());
//    }

    // 3) 호출 순서
    //  - InOrder
    @Test
    public void kooTest() {
//        SUT sut = new SUT();
//        List<String> mock = mock(List.class);
        InOrder inOrder = inOrder(mock);

        sut.koo(mock);

        inOrder.verify(mock).add("first");
        inOrder.verify(mock).add("second");
        inOrder.verify(mock).add("third");
    }

    // Mock은 Stub의 기능도 제공할 수 있습니다. - when
    @Test
    public void stubTest() {
//        List<String> mock = mock(List.class);
        when(mock.size())
                .thenReturn(100)
                .thenReturn(200)
                .thenReturn(300);
                // .thenThrow(new RuntimeException("..."));

        System.out.println(mock.size());
        System.out.println(mock.size());
        System.out.println(mock.size());
        System.out.println(mock.size());
    }

}

class SUT {
    public void koo(List<String> s) {
        s.add("first");
        s.add("second");
        s.add("third");
    }

    public void hoo(Point point) {
        point.move(10, 20);
        point.move(10, 30);
        point.move(10, 40);
    }

    public void goo(List<String> s) {
        s.add("once");
        s.add("twice");
        s.add("twice");
    }

    public void foo(List<String> s) {
        s.add("one");
        s.add("two");
    }
}

class Point {
    public void move(int x, int y) {

    }
}