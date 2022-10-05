package fr.hndgy.restaurantapp.application.port.in;

import fr.hndgy.restaurantapp.domain.Table.TableId;
import lombok.Value;

@Value
public class CreateOrderCommand {
    private TableId tableId;
    private Integer nbOfGuests;
}
