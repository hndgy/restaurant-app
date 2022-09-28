package fr.hndgy.restaurantapp.application.port.in;

import fr.hndgy.restaurantapp.domain.Table.TableId;

public interface GetBillQuery {
    double getBill(TableId tableId);
}
