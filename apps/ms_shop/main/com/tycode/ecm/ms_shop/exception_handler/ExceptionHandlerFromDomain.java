package com.tycode.ecm.ms_shop.exception_handler;

import com.tycode.ecm.shared.domain.value_object.InvalidUuidException;
import com.tycode.ecm.shared.infrastructure.exception.StandarzedExceptionResponse;
import com.tycode.ecm.shop.product.domain.InvalidProductPriceException;
import com.tycode.ecm.shop.product.domain.InvalidProductStockQuantityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerFromDomain {

    @ExceptionHandler(value = {InvalidProductPriceException.class})
    public ResponseEntity<StandarzedExceptionResponse> handleInvalidProductPriceException(InvalidProductPriceException exception){
        return new ResponseEntity<>(
                new StandarzedExceptionResponse(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage(), "error"),
                HttpStatus.NOT_ACCEPTABLE
        );
    }

    @ExceptionHandler(value = {InvalidUuidException.class})
    public ResponseEntity<StandarzedExceptionResponse> handleInvalidUuidException(InvalidUuidException exception){
        return new ResponseEntity<>(
                new StandarzedExceptionResponse(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage(), "error"),
                HttpStatus.NOT_ACCEPTABLE
        );
    }

    @ExceptionHandler(value = {InvalidProductStockQuantityException.class})
    public ResponseEntity<StandarzedExceptionResponse> handleInvalidProductStockQuantityException(InvalidProductStockQuantityException exception){
        return new ResponseEntity<>(
                new StandarzedExceptionResponse(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage(), "error"),
                HttpStatus.NOT_ACCEPTABLE
        );
    }
}
