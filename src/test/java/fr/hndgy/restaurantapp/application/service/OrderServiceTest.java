package fr.hndgy.restaurantapp.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.hndgy.restaurantapp.application.port.in.CreateOrderCommand;
import fr.hndgy.restaurantapp.application.port.out.MenuElementRepository;
import fr.hndgy.restaurantapp.application.port.out.OrderRepository;
import fr.hndgy.restaurantapp.application.port.out.TableRepository;
import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.Table;
import fr.hndgy.restaurantapp.domain.Table.TableId;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    OrderServiceImpl orderService;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    TableRepository tableRepository;

    @MockBean
    MenuElementRepository menuElementRepository;

    @Test
    public void createOrderTest(){
        //Arrange
        TableId tableId = TableId.generate();
        Table table = Table.of(tableId,"Test table");
        Order order = Order.withTableAndNbGuests(table, 2);

        when(this.tableRepository.getById(tableId)).thenReturn(table);
        when(this.orderRepository.createOrder(any(Order.class))).thenReturn(order);

        //Act
        Order saved = this.orderService.createOrder(new CreateOrderCommand(tableId,2));

        //Assert
        assertEquals(0,saved.getNbElement() );
        assertEquals(saved.getTable().getTableId(), tableId);
        verify(this.tableRepository, times(1)).getById(any(TableId.class));
        verify(this.orderRepository, times(1)).createOrder(any(Order.class));
    }

    @Test
    public void addChoiceTest(){
        //TODO
    }
}
