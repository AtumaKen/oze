package com.oze.kelechi_oze.exception;

public class BadRequestException extends BaseException {

    public BadRequestException(String code, String message) {
        super(code, message);
    }
}
