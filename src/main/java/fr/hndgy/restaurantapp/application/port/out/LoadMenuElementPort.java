package fr.hndgy.restaurantapp.application.port.out;

import fr.hndgy.restaurantapp.domain.MenuElement;

public interface LoadMenuElementPort {
    MenuElement loadMenuElement(MenuElement.MenuElementId menuElementId);
}
