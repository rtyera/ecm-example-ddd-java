package com.tycode.ecm.ms_shop.exception_handler;

import com.tycode.ecm.shared.infrastructure.exception.StandarzedExceptionResponse;
import com.tycode.ecm.shop.product.application.search_by_id.ProductNotExistException;
import com.tycode.ecm.shop.review.application.search_by_id.ReviewNotExistException;
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

    @ExceptionHandler(value = {ReviewNotExistException.class})
    public ResponseEntity<StandarzedExceptionResponse> handleReviewNotExistException(ReviewNotExistException exception){
        return new ResponseEntity<>(
                new StandarzedExceptionResponse(HttpStatus.NO_CONTENT.value(), exception.getMessage(), "error"),
                HttpStatus.NO_CONTENT
        );
    }
}
