package fr.hndgy.restaurantapp.domain;

import java.util.List;

import fr.hndgy.restaurantapp.domain.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {
    private OrderId orderId;
    private final Table table;
    private final List<MenuElement> choices;

    public static Order withoutId(Table table, List<MenuElement> choices) {
        return new Order(table, choices);
    }

    public double getTotalPrice(){

        double res = 0d;
        for (MenuElement menuElement : choices) {
            res+= menuElement.getPrice();
        }
        return res;
    }

    public double getTotalFoodPrice(){
        double res = 0d;
        for (MenuElement menuElement : choices) {
            if(menuElement.getType().equals(MenuElement.MenuElementType.FOOD))
                res+= menuElement.getPrice();
        }
        return res;
    }

    public double getTotalDrinkPrice(){
        double res = 0d;
        for (MenuElement menuElement : choices) {
            if(menuElement.getType().equals(MenuElement.MenuElementType.DRINK))
                res+= menuElement.getPrice();
        }
        return res;
    }

    public int getNbElement(){
        return this.choices.size();
    }

    public boolean addMenuElement(MenuElement menuElement){
        return choices.add(menuElement);
    }


    @Value
    public static class OrderId{
        private final Long value;
    }

}
