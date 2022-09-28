package fr.hndgy.restaurantapp.application.port.out;

import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.Table;

public interface LoadOrderPort {
    Order loadOrder(Order.OrderId orderId);
    Order loadOrder(Table.TableId tableId);
}
