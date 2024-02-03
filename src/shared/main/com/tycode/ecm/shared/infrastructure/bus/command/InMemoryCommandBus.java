package com.tycode.ecm.shared.infrastructure.bus.command;

import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.command.Command;
import com.tycode.ecm.shared.domain.bus.command.CommandBus;
import com.tycode.ecm.shared.domain.bus.command.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;

@Service
@RequiredArgsConstructor
public final class InMemoryCommandBus implements CommandBus {
    private final CommandHandlersInformation information;
    private final ApplicationContext         context;

    @Override
    public void dispatch(Command command) {
        Class<? extends CommandHandler> commandHandlerClass = information.search(command.getClass());

        CommandHandler handler = context.getBean(commandHandlerClass);

        handler.handle(command);
    }
}
