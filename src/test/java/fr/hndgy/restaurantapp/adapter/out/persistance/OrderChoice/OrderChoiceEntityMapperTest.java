package fr.hndgy.restaurantapp.adapter.out.persistance.OrderChoice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.hndgy.restaurantapp.adapter.out.persistance.menuElement.MenuElementEntity;
import fr.hndgy.restaurantapp.adapter.out.persistance.menuElement.MenuElementEntityMapper;
import fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice.OrderChoiceEntity;
import fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice.OrderChoiceEntityMapper;
import fr.hndgy.restaurantapp.domain.MealCategory;
import fr.hndgy.restaurantapp.domain.MenuElement;
import fr.hndgy.restaurantapp.domain.OrderChoice;
import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementId;
import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementType;

@SpringBootTest
public class OrderChoiceEntityMapperTest {
    
    @Autowired
    OrderChoiceEntityMapper orderChoiceEntityMapper;

    @MockBean
    MenuElementEntityMapper menuElementEntityMapper;

    @Test
    public void toEntity(){
        MenuElement menuElement = MenuElement.of(MenuElementId.generate(), "test", 1., MenuElementType.DRINK);

        OrderChoice orderChoice = OrderChoice.of(
            menuElement,
            "test",
            MealCategory.DESSERTS);

        MenuElementEntity menuElementEntity = new MenuElementEntity();
        menuElementEntity.setId(menuElement.getMenuElementId().getValue());
        menuElementEntity.setName(menuElement.getName());
        menuElementEntity.setPrice(menuElement.getPrice());
        menuElementEntity.setType(menuElement.getType());


        when(this.menuElementEntityMapper.toEntity(any(MenuElement.class)))
            .thenReturn(menuElementEntity);

        OrderChoiceEntity orderChoiceEntity = this.orderChoiceEntityMapper.toEntity(orderChoice);

        assertEquals(orderChoice.getOrderChoiceId().getValue(), orderChoiceEntity.getId());
        assertEquals(orderChoice.getComment(), orderChoiceEntity.getComment());
        verify(this.menuElementEntityMapper,times(1)).toEntity(any(MenuElement.class));
        assertNotNull(orderChoiceEntity.getMenuElement());
    }

    @Test
    public void toDomain(){

        MenuElementEntity menuElementEntity = new MenuElementEntity();
        menuElementEntity.setId(UUID.randomUUID());
        menuElementEntity.setName("test");
        menuElementEntity.setPrice(12.);
        menuElementEntity.setType(MenuElementType.FOOD);

        MenuElement menuElement = MenuElement.of(
            MenuElementId.of(menuElementEntity.getId()),
            menuElementEntity.getName(),
            menuElementEntity.getPrice(),
            menuElementEntity.getType());

        OrderChoiceEntity orderChoiceEntity = new OrderChoiceEntity();
        orderChoiceEntity.setId(UUID.randomUUID());
        orderChoiceEntity.setComment("test");;
        orderChoiceEntity.setMenuElement(menuElementEntity);



        OrderChoice orderChoice = this.orderChoiceEntityMapper.toDomainObject(orderChoiceEntity);

        assertEquals(orderChoice.getOrderChoiceId().getValue(), orderChoiceEntity.getId());
        assertEquals(orderChoice.getComment(), orderChoiceEntity.getComment());
        verify(this.menuElementEntityMapper, times(1)).toDomainObject(any(MenuElementEntity.class));
        assertNotNull(orderChoiceEntity.getMenuElement());
    }

}
