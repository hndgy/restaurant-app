package fr.hndgy.restaurantapp.domain;

import lombok.Getter;
import lombok.Value;

@Getter
public abstract class MenuElement {

    private MenuElementId menuElementId;
    private final double price;
    private final MenuElementType type;
    private final String instructions;

    protected MenuElement(double price, MenuElementType type, String instructions) {
        this.price = price;
        this.type = type;
        this.instructions = instructions;
    }

    public static enum MenuElementType{
        FOOD, DRINK
    }

    @Value
    public static class MenuElementId{
        private final Long value;
    }
}
