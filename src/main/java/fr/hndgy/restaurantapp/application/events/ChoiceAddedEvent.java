package fr.hndgy.restaurantapp.application.events;

import java.time.Clock;

import org.springframework.context.ApplicationEvent;

import fr.hndgy.restaurantapp.domain.OrderChoice;
import fr.hndgy.restaurantapp.domain.Order.OrderId;
import lombok.Getter;

@Getter
public class ChoiceAddedEvent extends ApplicationEvent{

    private OrderChoice orderChoice;
    private OrderId orderId;

    public ChoiceAddedEvent(Object source, OrderChoice orderChoice, OrderId orderId) {
        super(source);
        this.orderChoice = orderChoice;
        this.orderId = orderId;
    }
}
