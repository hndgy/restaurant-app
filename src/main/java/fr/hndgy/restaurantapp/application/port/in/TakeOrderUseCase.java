package fr.hndgy.restaurantapp.application.port.in;

import fr.hndgy.restaurantapp.domain.Order;

public interface TakeOrderUseCase {
    Order takeOrder(TakeOrderCommand takeOrderCommand);
}
