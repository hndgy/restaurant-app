package fr.hndgy.restaurantapp.application.port.out;

import java.util.List;

import fr.hndgy.restaurantapp.domain.MenuElement;
import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementId;

public interface MenuElementRepository {

    MenuElement getById(MenuElementId idElement);

    MenuElement create(MenuElement foodWithoutId);

    List<MenuElement> getAll();
    
}
