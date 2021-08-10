package com.purusottam.instagram.exception;

public enum ErrorCode {
    PROFILE_NOT_FOUND("Profile not found !!", 404),
    PROFILE_IS_EXIST("Profile is exist !!", 400),
    EMAIL_ID_EXISTS("EmailId is exists !!",400);
    private String message;
    private int errorCode;

    private ErrorCode(String message) {
        this.message = message;
    }

    private ErrorCode(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
