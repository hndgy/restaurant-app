package fr.hndgy.restaurantapp.application.port.in;

import java.util.List;
import java.util.Map;

import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementId;
import fr.hndgy.restaurantapp.domain.Table.TableId;
import lombok.Value;

@Value
public class CreateOrderCommand {
    private Map<MenuElementId,String> menuElementsAndComment;
    private TableId tableId;
}
