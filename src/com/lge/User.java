package com.lge;

public class User {
    protected int age = 42;
    protected int getAge() {
        return age;
    }

    private int n = 100;
    // private 필드에 대해서 테스트에서 확인해야 하는 상태가 있다면
    // 최소 protected 형태로 getter를 제공하는 것이 맞다.

    // 로버트 C 마틴 - Clean code의 저자
    // - private 메소드는 public 메소드의 가독성을 높이기 위해
    //   사용해야 한다.
    // - 테스트되지 않은 private 메소드 보다 테스트된 public 메소드가
    //   안전하다.
    private void foo() {

    }
}
