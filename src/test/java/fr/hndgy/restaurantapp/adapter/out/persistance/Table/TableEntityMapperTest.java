package fr.hndgy.restaurantapp.adapter.out.persistance.Table;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.hndgy.restaurantapp.adapter.out.persistance.table.TableEntity;
import fr.hndgy.restaurantapp.adapter.out.persistance.table.TableEntityMapper;
import fr.hndgy.restaurantapp.domain.Table;
import fr.hndgy.restaurantapp.domain.Table.TableId;

@SpringBootTest
public class TableEntityMapperTest {

    @Autowired
    TableEntityMapper tableEntityMapper;

    @Test
    public void toEntity(){
        var tableId = TableId.generate();
        var name = "test";
        var domain = Table.of(tableId, name);

        var entity = this.tableEntityMapper.toEntity(domain);

        assertEquals(tableId.getValue(), entity.getId());
        assertEquals(name, entity.getName());
    }
    
    @Test
    public void toDomain(){
        var entity = new TableEntity();
        entity.setId(UUID.randomUUID());
        entity.setName("test");

        var domain = this.tableEntityMapper.toDomainObject(entity);

        assertEquals(entity.getId(), domain.getTableId().getValue());
        assertEquals(entity.getName(), domain.getName());
    }
}
