package com.tycode.ecm.ms_seller.exception_handler;

import com.tycode.ecm.shared.domain.bus.event.NotRegisteredSubscriberException;
import com.tycode.ecm.shared.infrastructure.exception.StandarzedExceptionResponse;
import org.springframework.amqp.AmqpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerFromInfrastructure {

    @ExceptionHandler(value = {AmqpException.class})
    public ResponseEntity<StandarzedExceptionResponse> handleAmqpException(AmqpException exception){
        return new ResponseEntity<>(
                new StandarzedExceptionResponse(HttpStatus.FAILED_DEPENDENCY.value(), exception.getMessage(), "error"),
                HttpStatus.FAILED_DEPENDENCY
        );
    }

    @ExceptionHandler(value = {NotRegisteredSubscriberException.class})
    public ResponseEntity<StandarzedExceptionResponse> handleNotRegisteredSubscriberException(NotRegisteredSubscriberException exception){
        return new ResponseEntity<>(
                new StandarzedExceptionResponse(HttpStatus.FAILED_DEPENDENCY.value(), exception.getMessage(), "error"),
                HttpStatus.FAILED_DEPENDENCY
        );
    }

}
