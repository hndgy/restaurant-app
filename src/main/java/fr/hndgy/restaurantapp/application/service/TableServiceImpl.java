package fr.hndgy.restaurantapp.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.hndgy.restaurantapp.application.port.in.CreateTableCommand;
import fr.hndgy.restaurantapp.application.port.in.TableService;
import fr.hndgy.restaurantapp.application.port.out.OrderRepository;
import fr.hndgy.restaurantapp.application.port.out.TableRepository;
import fr.hndgy.restaurantapp.domain.Table;
import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.Table.TableId;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService{
    private final TableRepository tableRepository;
    private final OrderRepository orderRepository;

    @Override
    public List<Table> getAllTableDispo() {
        
        List<TableId> takenTables = this.orderRepository.getAllNotEndedOrders()
                            .stream()
                            .map(Order::getTable)
                            .map(Table::getTableId)
                            .distinct()
                            .toList();

       return this.tableRepository.getAll().stream()
            .filter((t) -> !takenTables.contains(t.getTableId()) )
            .toList();
    }

    @Override
    public List<Table> getAll() {
        return this.tableRepository.getAll();
    }

    @Override
    public Table createTable(CreateTableCommand command) {
        var table = Table.of(command.getName());
        return this.tableRepository.create(table);
    }


}
