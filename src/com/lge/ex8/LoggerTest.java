package com.lge.ex8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoggerTest {
    @DisplayName("파일명이 다섯글자 이상일 때, isValidFailename이 true를 반환하는지 검증")
    @Test
    public void isValidFilenameTest1() {
        String validFilename = "valid.log";
        Logger logger = new Logger();

        boolean actual = logger.isVaildFilename(validFilename);

        assertTrue(actual, "파일명이 다섯글자 이상일 때");
    }

    @DisplayName("파일명이 다섯글자 미만일 때, isValidFailename이 false를 반환하는지 검증")
    @Test
    public void isValidFilenameTest2() {
        String badFilename = "bad.log";
        Logger logger = new Logger();

        boolean actual = logger.isVaildFilename(badFilename);

        assertFalse(actual, "파일명이 다섯글자 미만일 때");
    }
}

//---------------------
class FileSystemManager {
    public boolean isValid(String filename) {
        // ...
        return true;
    }
}

class Logger {
    // file.log
    //  : 확장자를 제외한 파일의 이름이 5글자 이상이어야 한다.
    public boolean isVaildFilename(String filename) {
        String name = filename.split("\\.")[0];
        if (name.length() < 5)
            return false;

        FileSystemManager manager = new FileSystemManager();
        return manager.isValid(filename);
    }
}