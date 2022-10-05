package fr.hndgy.restaurantapp.application.port.in;

import fr.hndgy.restaurantapp.domain.Order.OrderId;
import fr.hndgy.restaurantapp.domain.OrderChoice.OrderChoiceId;
import lombok.Data;

@Data
public class RemoveChoiceCommand {
    private OrderId orderId;
    private OrderChoiceId orderChoiceId;
}
