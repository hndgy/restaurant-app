package fr.hndgy.restaurantapp.domain;

import java.util.List;

import fr.hndgy.restaurantapp.domain.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Order {
    private OrderId orderId;
    private final Table table;
    private final List<OrderChoice> choices;


    public double getTotalPrice(){

        double res = 0d;
        for (OrderChoice orderChoice  : choices) {
            res+= orderChoice.getMenuElement().getPrice();
        }
        return res;
    }

    public double getTotalFoodPrice(){
        double res = 0d;
        for (OrderChoice orderChoice : choices) {
            if(orderChoice.getMenuElement().getType().equals(MenuElement.MenuElementType.FOOD))
                res+= orderChoice.getMenuElement().getPrice();
        }
        return res;
    }

    public double getTotalDrinkPrice(){
        double res = 0d;
        for (OrderChoice orderChoice : choices) {
            if(orderChoice.getMenuElement().getType().equals(MenuElement.MenuElementType.DRINK))
                res+= orderChoice.getMenuElement().getPrice();
        }
        return res;
    }

    public int getNbElement(){
        return this.choices.size();
    }

    public boolean addChoice(MenuElement menuElement, String comment){
        OrderChoice orderChoice = new OrderChoice(this, menuElement, comment);
        return choices.add(orderChoice);
    }


    @Value
    public static class OrderId{
        private final Long value;
    }

}
