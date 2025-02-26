package com.api.bloodbankapi.commons.exception;

public class DonorAlreadyScreenedCustomException extends RuntimeException {

    private static final String RETURN_MESSAGE = "Donor already screened";

    private static final String CODE = "S_001";

    public DonorAlreadyScreenedCustomException(String message) {
        super(message);
    }

    public DonorAlreadyScreenedCustomException() {
        super(RETURN_MESSAGE);
    }

    public String getCode() {
        return CODE;
    }

}
