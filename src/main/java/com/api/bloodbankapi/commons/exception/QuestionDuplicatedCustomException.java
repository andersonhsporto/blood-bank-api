package com.api.bloodbankapi.commons.exception;

public class QuestionDuplicatedCustomException extends RuntimeException {

    private static final String RETURN_MESSAGE = "Question already registered";

    private static final String CODE = "Q_002";

    public QuestionDuplicatedCustomException(String message) {
        super(message);
    }

    public QuestionDuplicatedCustomException() {
        super(RETURN_MESSAGE);
    }

    public String getCode() {
        return CODE;
    }

}
