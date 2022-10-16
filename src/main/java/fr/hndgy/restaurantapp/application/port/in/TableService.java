package fr.hndgy.restaurantapp.application.port.in;

import java.util.List;

import fr.hndgy.restaurantapp.domain.Table;

public interface TableService {
    List<Table> getAllTableDispo();

    List<Table> getAll();

    Table createTable(CreateTableCommand command);
}
