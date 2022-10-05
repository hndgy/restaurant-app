package fr.hndgy.restaurantapp.adapter.out.persistance.order;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice.OrderChoiceEntityMapper;

public class OrderEntityMapperTest {
    
    @InjectMocks
    OrderEntityMapper orderEntityMapper;

    @MockBean
    OrderChoiceEntityMapper orderChoiceEntityMapper;

    @Test
    public void toEntity(){

    }
    
    @Test
    public void toDomain(){

    }
}
