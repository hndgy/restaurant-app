package fr.hndgy.restaurantapp.application.port.in;


import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementId;
import fr.hndgy.restaurantapp.domain.Order.OrderId;
import lombok.Data;

@Data
public class AddChoiceCommand {
    private MenuElementId menuElementId;
    private OrderId orderId;
    private String comment;
}
