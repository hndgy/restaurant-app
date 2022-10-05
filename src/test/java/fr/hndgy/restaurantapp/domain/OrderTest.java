package fr.hndgy.restaurantapp.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

import fr.hndgy.restaurantapp.domain.Table.TableId;

public class OrderTest {

    @Test
    void getNbElementWithoutChoice(){
        TableId tableId = TableId.generate();
        Table table = Table.of(tableId,"Test table");
        Order order = Order.withTableAndNbGuests(table,2);

        assertEquals(0, order.getNbElement());
    }

}
