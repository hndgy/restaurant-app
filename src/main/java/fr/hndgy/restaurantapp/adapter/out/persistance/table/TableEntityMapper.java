package fr.hndgy.restaurantapp.adapter.out.persistance.table;

import org.springframework.stereotype.Component;

import fr.hndgy.restaurantapp.domain.Table;

@Component
public class TableEntityMapper {
    public TableEntity toEntity(Table table){
        TableEntity entity = new TableEntity();
        entity.setId(table.getTableId().getValue());
        entity.setName(table.getName());
        return entity;
    }
}
