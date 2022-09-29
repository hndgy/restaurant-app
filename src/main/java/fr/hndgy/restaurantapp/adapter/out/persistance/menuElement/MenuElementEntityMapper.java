package fr.hndgy.restaurantapp.adapter.out.persistance.menuElement;

import org.springframework.stereotype.Component;

import fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice.OrderChoiceEntity;
import fr.hndgy.restaurantapp.domain.MenuElement;

@Component
public class MenuElementEntityMapper {

    public MenuElementEntity toEntity(MenuElement menuElement){
        MenuElementEntity entity = new MenuElementEntity();

        entity.setId(menuElement.getMenuElementId().getValue());
        return entity;
    }

    public MenuElement toDomainObject(MenuElementEntity entity) {
        MenuElement menuElement = new MenuElement(
            new MenuElement.MenuElementId(entity.getId()),
            entity.getPrice(),
            entity.getType()
            );
        return menuElement;
    }
}
