package com.lge.ex8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoggerTest2 {
    private Logger logger;

    @BeforeEach
    public void setUp() {
        logger = new Logger();
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

// 4. 테스트에서 제품 코드를 테스트 대역을 적용하는 형태로 변경하는 리팩토링을 '틈새 만들기'라고 합니다.


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
    // file.log
    //  : 확장자를 제외한 파일의 이름이 5글자 이상이어야 한다.
    public boolean isVaildFilename(String filename) {
        //=========
        String name = filename.split("\\.")[0];
        System.out.println(name);
        if (name.length() < 5)
            return false;
        //=========

        IFileSystemManager manager = new FileSystemManager();
        return manager.isValid(filename);
    }
}