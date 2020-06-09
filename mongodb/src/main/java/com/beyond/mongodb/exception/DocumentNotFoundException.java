package com.beyond.mongodb.exception;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/18
 */
public class DocumentNotFoundException extends RuntimeException {

    private static final String DEFAULT_STRING = "找不到数据";

    public DocumentNotFoundException() {
        this(DEFAULT_STRING);
    }

    public DocumentNotFoundException(String message) {
        super(message);
    }
}
