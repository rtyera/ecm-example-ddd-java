package com.tycode.ecm.shared.domain.bus.command;

public interface CommandBus {

    void dispatch(Command command);
}
