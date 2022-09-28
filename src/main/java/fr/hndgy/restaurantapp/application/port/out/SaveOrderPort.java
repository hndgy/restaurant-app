package fr.hndgy.restaurantapp.application.port.out;

import fr.hndgy.restaurantapp.domain.Order;

public interface SaveOrderPort {
    Order saveOrder(Order order);
}
