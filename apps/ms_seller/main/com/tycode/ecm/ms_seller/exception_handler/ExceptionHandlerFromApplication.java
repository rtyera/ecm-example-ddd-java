package com.tycode.ecm.ms_seller.exception_handler;

import com.tycode.ecm.shared.infrastructure.exception.StandarzedExceptionResponse;
import com.tycode.ecm.seller.product.application.search_by_id.ProductNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerFromApplication {

    @ExceptionHandler(value = {ProductNotExistException.class})
    public ResponseEntity<StandarzedExceptionResponse> handleInvalidProductPriceException(ProductNotExistException exception){
        return new ResponseEntity<>(
                new StandarzedExceptionResponse(HttpStatus.NO_CONTENT.value(), exception.getMessage(), "error"),
                HttpStatus.NO_CONTENT
        );
    }

}
