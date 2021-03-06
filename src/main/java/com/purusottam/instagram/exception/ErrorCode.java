package com.purusottam.instagram.exception;

public enum ErrorCode {
    PROFILE_NOT_FOUND("Profile not found !!", 404),
    PROFILE_IS_EXIST("Profile is exist !!", 400),
    EMAIL_ID_EXISTS("EmailId is exists !!", 400),
    POST_NOT_FOUND("Post not found !!", 404),
    POST_ALREADY_EXISTS("Post is already exist !!", 400),
    LIKE_iS_EXIST("Like is already exist !!", 400),
    LIKE_NOT_FOUND("Like not found !!", 404),
    COMMENT_NOT_FOUND("Comment not found !!", 404);
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
