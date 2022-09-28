package fr.hndgy.restaurantapp.application.port.in;

import java.util.List;

import fr.hndgy.restaurantapp.domain.MenuElement;
import fr.hndgy.restaurantapp.domain.Table;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class TakeOrderCommand {
    private final List<MenuElement.MenuElementId> choices;
    private final Table.TableId tableId;
}
