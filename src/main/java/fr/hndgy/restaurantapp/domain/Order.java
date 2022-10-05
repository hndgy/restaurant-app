package fr.hndgy.restaurantapp.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.hndgy.restaurantapp.domain.Table;
import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementId;
import fr.hndgy.restaurantapp.domain.OrderChoice.OrderChoiceId;
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


    public static Order withTable(Table table){
        return new Order(OrderId.generate(), table, new ArrayList<>());
    }

    public static Order of(OrderId orderId, Table table, List<OrderChoice> choices){
        return new Order(orderId, table, choices);
    }

    public int getNbElement(){
        return this.choices.size();
    }

    public OrderChoice addChoice(MenuElement menuElement, String comment){
        OrderChoice orderChoice = OrderChoice.of(menuElement, comment);
        choices.add(orderChoice);
        return orderChoice;
    }

    public boolean removeChoice(OrderChoiceId orderChoiceId){
        return this.choices.removeIf(oc -> oc.getOrderChoiceId().equals(orderChoiceId));
    }


    @Value
    public static class OrderId{
        private final UUID value;
        public static OrderId of(UUID uuid){
            return new OrderId(uuid);
        }
        public static OrderId generate(){
            return new OrderId(UUID.randomUUID());
        }
    }

}
