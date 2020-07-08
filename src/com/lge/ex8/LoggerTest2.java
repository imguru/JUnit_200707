package com.lge.ex8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 테스트 대역을 만드는 방법.
// 1. 협력 객체의 인터페이스를 기반으로 생성한다.
class TestDouble implements IFileSystemManager {
    @Override
    public boolean isValid(String filename) {
        return true;
    }
}


public class LoggerTest2 {
    private Logger logger;
    private TestDouble stubFileSystem;

    @BeforeEach
    public void setUp() {
        stubFileSystem = new TestDouble();
        logger = new Logger(stubFileSystem);
    }


    @DisplayName("파일명이 다섯글자 이상일 때, isValidFailename이 true를 반환하는지 검증")
    @Test
    public void isValidFilenameTest1() {
        String validFilename = "valid.log";

        boolean actual = logger.isVaildFilename(validFilename);

        assertTrue(actual, "파일명이 다섯글자 이상일 때");
    }

    @DisplayName("파일명이 다섯글자 미만일 때, isValidFailename이 false를 반환하는지 검증")
    @Test
    public void isValidFilenameTest2() {
        String badFilename = "bad.log";

        boolean actual = logger.isVaildFilename(badFilename);

        assertFalse(actual, "파일명이 다섯글자 미만일 때");
    }
}

//---------------------
// 1. 아래의 코드는 테스트 대역을 적용할 수 없는 형태의 코드입니다.
// 2. 테스트 대역을 적용하기 위해서는, 제품 코드의 설계가 테스트 대역을 적용할 수 있어야 하는 설계이어야 합니다.
// 3. 테스트 대역을 적용하기 위한 설계의 핵심 - 약한 결합(느슨한 결합)
//   강한 결합: 의존하는 객체의 구체적인 타입에 의존하는 것
//   약한 결합: 의존하는 객체의 '추상 클래스나 인터페이스 타입'에 의존하는 것
//     1) 협력 객체의 인터페이스를 설계한다.
//     2) 협력 객체를 이용할 때 인터페이스를 통해 이용한다.
//     3) 협력 객체를 직접 생성하는 것이 아니라, 외부에서 생성해서 전달받아야 한다.!!!!
//       => DI(Dependency Injection)
//        1) 생성자 주입 - 협력 객체가 필수적일 때
//        2) 메소드 주입 - 협력 객체가 필수적이지 않을 때

// 4. 테스트에서 제품 코드를 테스트 대역을 적용하는 형태로 변경하는 리팩토링을 '틈새 만들기'라고 합니다.
// 5. 의존성 주입
//    문제점: '보일러플레이트'가 발생한다.
//    보일러플레이트: 반드시 필요하지만, 반복적으로 발생하는 코드

//    A -> B, C, D
//    A a = new A(new B(), new C(), new D());
//   : 직접 의존성 주입에 코드를 작성하는 것은, '가난한자의 의존성 주입'이라고 합니다.
//   => 의존성 주입 프레임워크를 이용하면, 효과적으로 의존성 주입을 적용할 수 있습니다.
//     - Dagger

interface IFileSystemManager {
    boolean isValid(String filename);
}

class FileSystemManager implements IFileSystemManager {
    @Override
    public boolean isValid(String filename) {
        // ...
        return false;
    }
}

class Logger {
    private IFileSystemManager manager;

    public Logger() {
        this(new FileSystemManager());
    }

    public Logger(IFileSystemManager manager) {
        this.manager = manager;
    }

    // file.log
    //  : 확장자를 제외한 파일의 이름이 5글자 이상이어야 한다.
    public boolean isVaildFilename(String filename) {
        //=========
        String name = filename.split("\\.")[0];
        if (name.length() < 5)
            return false;
        //=========

        // IFileSystemManager manager = new FileSystemManager();
        return manager.isValid(filename);
    }
}