package fr.hndgy.restaurantapp.adapter.out.persistance.MenuElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.hndgy.restaurantapp.adapter.out.persistance.menuElement.MenuElementEntity;
import fr.hndgy.restaurantapp.adapter.out.persistance.menuElement.MenuElementEntityMapper;
import fr.hndgy.restaurantapp.domain.MenuElement;
import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementId;
import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementType;

@SpringBootTest
public class MenuElementEntityMapperTest {
    
    @Autowired
    MenuElementEntityMapper menuElementEntityMapper;


    @Test
    public void toEntity(){
        MenuElement menuElement = MenuElement.of(
            MenuElementId.generate(),
            "test",
            12.,
            MenuElementType.FOOD);

        MenuElementEntity menuElementEntity = this.menuElementEntityMapper.toEntity(menuElement);

        assertEquals(menuElement.getMenuElementId().getValue(), menuElementEntity.getId());
        assertEquals(menuElement.getName(), menuElementEntity.getName());
        assertEquals(menuElement.getPrice(), menuElementEntity.getPrice());
        assertEquals(menuElement.getType(), menuElementEntity.getType());


    }


    @Test
    public void toDomain(){

        MenuElementEntity menuElementEntity = new MenuElementEntity();
        menuElementEntity.setId(UUID.randomUUID());
        menuElementEntity.setName("test");
        menuElementEntity.setPrice(12.);
        menuElementEntity.setType(MenuElementType.DRINK);

        MenuElement menuElement = this.menuElementEntityMapper.toDomainObject(menuElementEntity);

        assertEquals(menuElement.getMenuElementId().getValue(), menuElementEntity.getId());
        assertEquals(menuElement.getName(), menuElementEntity.getName());
        assertEquals(menuElement.getPrice(), menuElementEntity.getPrice());
        assertEquals(menuElement.getType(), menuElementEntity.getType());
    }
}
