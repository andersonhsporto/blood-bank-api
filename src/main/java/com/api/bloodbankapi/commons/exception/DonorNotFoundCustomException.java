package com.api.bloodbankapi.commons.exception;

import lombok.Getter;

public class DonorNotFoundCustomException extends RuntimeException {

    private static final String RETURN_MESSAGE = "Donor not found";

    private static final String CODE = "D_001";

    public DonorNotFoundCustomException(String message) {
        super(message);
    }

    public DonorNotFoundCustomException() {
        super(RETURN_MESSAGE);
    }

    public String getCode() {
        return CODE;
    }

}
