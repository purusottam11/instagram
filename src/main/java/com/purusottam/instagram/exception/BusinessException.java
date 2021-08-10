package com.purusottam.instagram.exception;
public class BusinessException extends RuntimeException {

    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(String message) {
        super(message);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}