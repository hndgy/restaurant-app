package fr.hndgy.restaurantapp.adapter.out.persistance.menuElement;

import org.springframework.stereotype.Component;

import fr.hndgy.restaurantapp.adapter.out.persistance.common.EntityMapper;
import fr.hndgy.restaurantapp.domain.MenuElement;

@Component
public class MenuElementEntityMapper implements EntityMapper<MenuElementEntity, MenuElement>{

    @Override
    public MenuElementEntity toEntity(MenuElement menuElement){
        MenuElementEntity entity = new MenuElementEntity();
        if(menuElement.getMenuElementId() != null){
            entity.setId(menuElement.getMenuElementId().getValue());
        }
        entity.setName(menuElement.getName());
        entity.setPrice(menuElement.getPrice());
        entity.setType(menuElement.getType());
        return entity;
    }

    @Override
    public MenuElement toDomainObject(MenuElementEntity entity) {

        MenuElement.MenuElementId id =  new MenuElement.MenuElementId(entity.getId());
        MenuElement menuElement = new MenuElement(
            id,
            entity.getName(),
            entity.getPrice(),
            entity.getType()
            );
        return menuElement;
    }
}
