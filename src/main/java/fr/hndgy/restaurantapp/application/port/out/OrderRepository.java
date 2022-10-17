package fr.hndgy.restaurantapp.application.port.out;

import java.util.List;

import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.Order.OrderId;
import fr.hndgy.restaurantapp.domain.OrderChoice.OrderChoiceId;

public interface OrderRepository {
    Order createOrder(Order order);
    Order getOrderById(OrderId orderId);
    void updateChoices(Order order);
    void removeChoice(OrderChoiceId orderChoiceId);
    List<Order> getAllOrders();
    List<Order> getAllNotEndedOrders();

    void deleteOrder(OrderId orderId);
}
