package com.api.bloodbankapi.commons.exception;

public class ScreeningNotFoundCustomException extends RuntimeException {
    private static final String RETURN_MESSAGE = "Screening not found for protocol";

    private static final String CODE = "S_001";

    public ScreeningNotFoundCustomException(String message) {
        super(message);
    }

    public ScreeningNotFoundCustomException() {
        super(RETURN_MESSAGE);
    }

    public String getCode() {
        return CODE;
    }
}
