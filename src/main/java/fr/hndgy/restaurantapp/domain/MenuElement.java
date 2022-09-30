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
    private final String name;
    private final Double price;
    private final MenuElementType type;


    public static MenuElement foodWithoutId(String name, Double price){
        return new MenuElement(name, price, MenuElementType.FOOD);
    }

    public static MenuElement drinkWithoutId(String name, Double price){
        return new MenuElement(name, price, MenuElementType.DRINK);
    }

    public static enum MenuElementType{
        FOOD, DRINK
    }

    @Value
    public static class MenuElementId{
        private final Long value;
    }

}
