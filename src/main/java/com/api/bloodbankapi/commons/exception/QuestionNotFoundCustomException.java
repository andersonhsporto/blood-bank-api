package com.api.bloodbankapi.commons.exception;

public class QuestionNotFoundCustomException extends RuntimeException {

    private static final String RETURN_MESSAGE = "Question not found";

    private static final String CODE = "Q_001";

    public QuestionNotFoundCustomException(String message) {
        super(message);
    }

    public QuestionNotFoundCustomException() {
        super(RETURN_MESSAGE);
    }

    public String getCode() {
        return CODE;
    }

}
