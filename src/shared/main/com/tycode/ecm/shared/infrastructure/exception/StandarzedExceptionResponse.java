package com.tycode.ecm.shared.infrastructure.exception;

public record StandarzedExceptionResponse(int httpStatus, String message, String type) { }
