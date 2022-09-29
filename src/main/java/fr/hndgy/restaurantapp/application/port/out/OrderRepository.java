package fr.hndgy.restaurantapp.application.port.out;

import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.Order.OrderId;

public interface OrderRepository {
    Order createOrder(Order order);
    Order getOrderById(OrderId orderId);
}
