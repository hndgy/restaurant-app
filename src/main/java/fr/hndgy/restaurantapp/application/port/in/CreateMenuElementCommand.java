package fr.hndgy.restaurantapp.application.port.in;

import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementType;
import lombok.Data;

@Data
public class CreateMenuElementCommand {
    private String name;
    private Double price;
    private MenuElementType type;
}
