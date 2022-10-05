package fr.hndgy.restaurantapp.adapter.out.persistance.order;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice.OrderChoiceEntityMapper;
import fr.hndgy.restaurantapp.domain.MenuElement;
import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.Table;
import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementId;
import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementType;
import fr.hndgy.restaurantapp.domain.Table.TableId;

@SpringBootTest
public class OrderEntityMapperTest {
    
    @InjectMocks
    OrderEntityMapper orderEntityMapper;

    @MockBean
    OrderChoiceEntityMapper orderChoiceEntityMapper;

    @Test
    void toEntityTest(){
       
    }
}
