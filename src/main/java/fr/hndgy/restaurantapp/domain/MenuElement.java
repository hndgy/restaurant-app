package fr.hndgy.restaurantapp.domain;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuElement {

    private MenuElementId menuElementId;
    private final String name;
    private final Double price;
    private final MenuElementType type;

    public static MenuElement of(MenuElementId id, String name, Double price, MenuElementType type){
        return new MenuElement(id, name, price,type);
    }

    public static MenuElement newFood(String name, Double price){
        return new MenuElement(MenuElementId.generate(), name, price, MenuElementType.FOOD);
    }

    public static MenuElement newDrink(String name, Double price){
        return new MenuElement(MenuElementId.generate(), name, price, MenuElementType.DRINK);
    }

    public static enum MenuElementType{
        FOOD, DRINK
    }

    @Value
    public static class MenuElementId{
        private final UUID value;
        public static MenuElementId of(UUID uuid){
            return new MenuElementId(uuid);
        }
        public static MenuElementId generate(){
            return new MenuElementId(UUID.randomUUID());
        }
    }

}
