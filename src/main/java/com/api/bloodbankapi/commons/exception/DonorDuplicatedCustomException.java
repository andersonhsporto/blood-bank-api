package com.api.bloodbankapi.commons.exception;

public class DonorDuplicatedCustomException extends RuntimeException {

    private static final String RETURN_MESSAGE = "Donor already registered";

    private static final String CODE = "D_002";

    public DonorDuplicatedCustomException(String message) {
        super(message);
    }

    public DonorDuplicatedCustomException() {
        super(RETURN_MESSAGE);
    }

    public String getCode() {
        return CODE;
    }

}
