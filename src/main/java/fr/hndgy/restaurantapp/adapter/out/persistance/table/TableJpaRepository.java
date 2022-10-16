package fr.hndgy.restaurantapp.adapter.out.persistance.table;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.hndgy.restaurantapp.application.port.out.TableRepository;
import fr.hndgy.restaurantapp.domain.Table;
import fr.hndgy.restaurantapp.domain.Table.TableId;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TableJpaRepository implements TableRepository{
    private final TableDAO tableDAO;
    private final TableEntityMapper tableEntityMapper;

    @Override
    public Table getById(TableId tableId) {
        TableEntity tableEntity = this.tableDAO.findById(tableId.getValue()).get();
        return this.tableEntityMapper.toDomainObject(tableEntity);
    }

    @Override
    public Table create(Table table) {
        TableEntity entity = this.tableEntityMapper.toEntity(table);
        TableEntity saved = this.tableDAO.save(entity);
        return this.tableEntityMapper.toDomainObject(saved);
    }

    @Override
    public List<Table> getAllDispo() {
       
        return this.tableDAO.findAllDispo()
                    .stream()
                    .map(this.tableEntityMapper::toDomainObject)
                    .toList();
    }

    @Override
    public List<Table> getAll() {
        
        return this.tableDAO.findAll()
                    .stream()
                    .map(this.tableEntityMapper::toDomainObject)
                    .toList();
    }
}
