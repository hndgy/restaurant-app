package fr.hndgy.restaurantapp.adapter.out.persistance.table;

import org.springframework.stereotype.Component;

import fr.hndgy.restaurantapp.adapter.out.persistance.common.EntityMapper;
import fr.hndgy.restaurantapp.domain.Table;
import fr.hndgy.restaurantapp.domain.Table.TableId;

@Component
public class TableEntityMapper implements EntityMapper<TableEntity, Table>{
    
    @Override
    public TableEntity toEntity(Table table){
        TableEntity entity = new TableEntity();
        if(table.getTableId() != null){
            entity.setId(table.getTableId().getValue());
        }
        entity.setName(table.getName());
        return entity;
    }

    @Override
    public Table toDomainObject(TableEntity entity) {
        TableId tableId = new TableId(entity.getId());
        Table domain = new Table(tableId, entity.getName());
        return domain;
    }
}
