package fr.hndgy.restaurantapp.application.port.out;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.hndgy.restaurantapp.domain.MenuElement;
import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.Table;

@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    TableRepository tableRepository;

    @Test
    void createOrder(){
        Table table = this.tableRepository.create(Table.of("Test table"));
        Order order = Order.withTableAndNbGuests(table,2);

        Order saved = this.orderRepository.createOrder(order);

        assertEquals(saved.getTable().getTableId(), order.getTable().getTableId());
        assertEquals(saved.getNbElement(), order.getNbElement());
        assertEquals(saved.getNbOfGuests(), order.getNbOfGuests());
        assertEquals(saved.getCreationDateTime(), order.getCreationDateTime());
        assertEquals(saved.getOrderId().getValue(), order.getOrderId().getValue());
    }



    @Test
    void updateOrder(){
        //TODO
    }



}
