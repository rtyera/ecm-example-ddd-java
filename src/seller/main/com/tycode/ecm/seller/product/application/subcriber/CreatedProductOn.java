package com.tycode.ecm.seller.product.application.subcriber;

import com.tycode.ecm.seller.product.application.create.CommandCreateProduct;
import com.tycode.ecm.seller.product.application.event.CreatedProductEvent;
import com.tycode.ecm.shared.domain.Service;
import com.tycode.ecm.shared.domain.bus.command.CommandBus;
import com.tycode.ecm.shared.domain.bus.event.EventSubscriber;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;

@Service
@EventSubscriber({CreatedProductEvent.class})
@AllArgsConstructor
public class CreatedProductOn {

    private final CommandBus commandBus;

    @EventListener
    public void on(CreatedProductEvent event){

        var c = new CommandCreateProduct(
                event.aggregateId(),
                event.getName(),
                Float.parseFloat(event.getPrice()),
                event.getImages(),
                Integer.parseInt(event.getStockQuantity())
        );
        this.commandBus.dispatch(c);
    }
}
