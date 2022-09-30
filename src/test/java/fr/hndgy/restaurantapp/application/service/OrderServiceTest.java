package fr.hndgy.restaurantapp.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

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
import fr.hndgy.restaurantapp.domain.MenuElement;
import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.Table;
import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementId;
import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementType;
import fr.hndgy.restaurantapp.domain.Table.TableId;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    OrderService orderService;

    @MockBean
    OrderRepository orderRepository;
    
    @MockBean
    TableRepository tableRepository;

    @MockBean
    MenuElementRepository menuElementRepository;

    @Test
    public void createOrderTest(){
        //Arrange
        TableId tableId = new TableId(999l);
        Table table = new Table(tableId,"Test table");
        Order order = Order.withTable(table);
        MenuElementId plat1id = new MenuElementId(1L);
        MenuElement plat1 = new MenuElement(plat1id,"BoBun", 10.,MenuElementType.FOOD);
        String comment = "sans oignon";
        order.addChoice(plat1, comment);

        when(this.menuElementRepository.getById(plat1id)).thenReturn(plat1);
        when(this.tableRepository.getById(tableId)).thenReturn(table);
        when(this.orderRepository.createOrder(any(Order.class))).thenReturn(order);

        //Act
        Order saved = this.orderService.createOrder(new CreateOrderCommand(Map.of(plat1.getMenuElementId(), comment), tableId));

        //Assert
        assertEquals(saved.getChoices().size(), order.getChoices().size());
        verify(this.orderRepository, times(1)).createOrder(any(Order.class));
        verify(this.menuElementRepository, times(1)).getById(any(MenuElementId.class));
        verify(this.tableRepository, times(1)).getById(any(TableId.class));
    }
}
