package fr.hndgy.restaurantapp.domain;

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

    public static Table withName(String name) {
        return new Table(name);
    }

    @Value
    public static class TableId {
        private final Long value;
    }

}
