package fr.hndgy.restaurantapp.application.events;

import org.springframework.context.ApplicationEvent;

import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.Order.OrderId;
import lombok.Getter;

@Getter
public class OrderDeletedEvent extends ApplicationEvent{

    private OrderId orderId;
    public OrderDeletedEvent(Object source, OrderId orderId) {
        super(source);
        this.orderId = orderId;
    }
    
}
