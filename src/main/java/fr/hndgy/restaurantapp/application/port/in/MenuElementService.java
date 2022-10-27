package fr.hndgy.restaurantapp.application.port.in;

import java.util.List;

import fr.hndgy.restaurantapp.domain.MenuElement;

public interface MenuElementService {
    List<MenuElement> getAllMenuElement();

    MenuElement create(CreateMenuElementCommand command);
}
