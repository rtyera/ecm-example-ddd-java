package com.tycode.ecm.shared.domain.value_object;

public class InvalidUuidException extends IllegalArgumentException{

    public InvalidUuidException(String message){
        super(message);
    }
}
