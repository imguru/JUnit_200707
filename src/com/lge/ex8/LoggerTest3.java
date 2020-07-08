package com.lge.ex8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class LoggerTest3 {
    private Logger logger;
    private IFileSystemManager stub;

    @BeforeEach
    public void setUp() {
        stub = mock(IFileSystemManager.class);
        when(stub.isValid(anyString())).thenReturn(true);   // !!!!!
        logger = new Logger(stub);
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

