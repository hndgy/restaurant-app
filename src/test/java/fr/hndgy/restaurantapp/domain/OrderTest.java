package fr.hndgy.restaurantapp.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import fr.hndgy.restaurantapp.domain.Table.TableId;

public class OrderTest {

    @Test
    void getNbElementWithoutChoice(){
        TableId tableId = TableId.generate();
        Table table = new Table(tableId,"Test table");
        Order order = Order.withTable(table);

        assertEquals(0, order.getNbElement());
    }

}
