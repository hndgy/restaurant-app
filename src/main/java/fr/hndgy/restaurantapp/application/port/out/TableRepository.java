package fr.hndgy.restaurantapp.application.port.out;


import fr.hndgy.restaurantapp.domain.Table;
import fr.hndgy.restaurantapp.domain.Table.TableId;

public interface TableRepository {

    Table getById(TableId tableId);

    Table create(Table withName);

}
