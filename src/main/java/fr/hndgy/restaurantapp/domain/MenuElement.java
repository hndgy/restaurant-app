package fr.hndgy.restaurantapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class MenuElement {

    private MenuElementId menuElementId;
    private final double price;
    private final MenuElementType type;

  

    public static enum MenuElementType{
        FOOD, DRINK
    }

    @Value
    public static class MenuElementId{
        private final Long value;
    }
}
