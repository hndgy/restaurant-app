package fr.hndgy.restaurantapp.domain;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Table {

    private TableId tableId;

    private final String name;

    public static Table of(TableId tableId,String name) {
        return new Table(tableId,name);
    }

    public static Table of(String name) {
        return new Table(TableId.generate(),name);
    }

    @Value
    public static class TableId {
        private final UUID value;
        public static TableId of(UUID uuid){
            return new TableId(uuid);
        }
        public static TableId generate(){
            return new TableId(UUID.randomUUID());
        }
    }

}
