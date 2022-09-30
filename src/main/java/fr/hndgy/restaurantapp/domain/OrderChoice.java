package fr.hndgy.restaurantapp.domain;

import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementId;
import fr.hndgy.restaurantapp.domain.Order.OrderId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderChoice {
    private final OrderId orderId;
    private final MenuElement menuElement;
    private final String comment;
}
