package fr.hndgy.restaurantapp.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderChoice {
    private final Order order;
    private final MenuElement menuElement;
    private final String comment;
}
