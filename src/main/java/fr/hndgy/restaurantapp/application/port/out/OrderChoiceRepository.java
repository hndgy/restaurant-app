package fr.hndgy.restaurantapp.application.port.out;

import java.util.List;

import fr.hndgy.restaurantapp.domain.OrderChoice;


public interface OrderChoiceRepository {

    void updateAll(List<OrderChoice> choices);
}
