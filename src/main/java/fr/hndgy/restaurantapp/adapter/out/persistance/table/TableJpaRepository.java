package fr.hndgy.restaurantapp.adapter.out.persistance.table;

import org.springframework.stereotype.Repository;

import fr.hndgy.restaurantapp.application.port.out.TableRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TableJpaRepository implements TableRepository{
    private final TableDAO tableDAO;
}
