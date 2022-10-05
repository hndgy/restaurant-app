package fr.hndgy.restaurantapp.application.events;

import org.springframework.context.ApplicationEvent;

import fr.hndgy.restaurantapp.domain.Order;
import lombok.Getter;

@Getter
public class OrderCreatedEvent extends ApplicationEvent{

    private Order order;

    public OrderCreatedEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }
}
