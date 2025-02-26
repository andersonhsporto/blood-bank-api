package com.api.bloodbankapi.commons.advice;

import com.api.bloodbankapi.commons.exception.DonorDuplicatedCustomException;
import com.api.bloodbankapi.commons.exception.DonorNotFoundCustomException;
import com.api.bloodbankapi.commons.model.ErrorApi;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class CustomControllerAdvice {

    @ExceptionHandler(DonorNotFoundCustomException.class)
    public ResponseEntity<ErrorApi> handleDonorNotFoundCustomException(DonorNotFoundCustomException e) {
        ErrorApi errorApi = new ErrorApi(e.getMessage(), e.getCode());

        log.error("Error DonorNotFoundCustomException: {}", errorApi);
        return new ResponseEntity<>(errorApi, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApi> handleException(Exception e) {
        ErrorApi errorApi = new ErrorApi(e.getMessage(), e.getClass().getSimpleName());

        log.error("Error Exception: {}", errorApi);
        return new ResponseEntity<>(errorApi, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorApi> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String message = "Invalid request body";
        ErrorApi errorApi = new ErrorApi(message, "G_001");

        log.error("Error HttpMessageNotReadableException: {}", errorApi);
        return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DonorDuplicatedCustomException.class)
    public ResponseEntity<ErrorApi> handleDonorDuplicatedCustomException(DonorDuplicatedCustomException e) {
        ErrorApi errorApi = new ErrorApi(e.getMessage(), e.getCode());

        log.error("Error DonorDuplicatedCustomException: {}", errorApi);
        return new ResponseEntity<>(errorApi, HttpStatus.CONFLICT);
    }
}
