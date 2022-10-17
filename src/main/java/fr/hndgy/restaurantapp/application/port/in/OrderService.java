package fr.hndgy.restaurantapp.application.port.in;

import java.util.List;

import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.OrderChoice;
import fr.hndgy.restaurantapp.domain.Order.OrderId;

public interface OrderService {

    Order createOrder(CreateOrderCommand command);

    OrderChoice addChoice(AddChoiceCommand command);

    void removeChoice(RemoveChoiceCommand command);

    Order getOrderById(OrderId orderId);

    List<Order> getAllPendingOrdersToday();

    List<Order> getAllOrders();

    void removeOrder(OrderId orderId);

}
