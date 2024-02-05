package com.tycode.ecm.shared.domain.bus.event;

public class NotRegisteredSubscriberException extends RuntimeException{

    public NotRegisteredSubscriberException(String message){
        super(message);
    }
}
