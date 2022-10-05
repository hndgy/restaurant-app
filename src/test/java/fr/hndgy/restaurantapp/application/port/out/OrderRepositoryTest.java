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
        Order order = Order.withTable(table);

        Order saved = this.orderRepository.createOrder(order);

        assertEquals(saved.getTable().getTableId(), saved.getTable().getTableId());
        assertNotNull(saved.getOrderId());
    }



    @Test
    void updateOrder(){
        //TODO
    }



}
