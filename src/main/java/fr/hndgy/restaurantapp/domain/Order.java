package fr.hndgy.restaurantapp.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.hndgy.restaurantapp.domain.OrderChoice.OrderChoiceId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {
    private OrderId orderId;
    private final Table table;
    private final List<OrderChoice> choices;
    private final Integer nbOfGuests;
    private final LocalDateTime creationDateTime;


    public static Order withTableAndNbGuests(Table table, int nbOfGuests){
        return new Order(OrderId.generate(), table, new ArrayList<>(), nbOfGuests, LocalDateTime.now());
    }

    public static Order of(OrderId orderId, Table table, List<OrderChoice> choices, int nbOfGuests, LocalDateTime creationDateTime){
        return new Order(orderId, table, choices, nbOfGuests, creationDateTime);
    }

    public int getNbElement(){
        return this.choices.size();
    }

    public double getTotalPrice(){
        double res = 0.;
        for(OrderChoice choice : this.choices){
            res += choice.getMenuElement().getPrice();
        }
        return res;
    }

    public double getPricePerPerson(){
        return this.getTotalPrice() / this.nbOfGuests;
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
