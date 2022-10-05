package fr.hndgy.restaurantapp.adapter.out.persistance.table;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TableDAO extends JpaRepository<TableEntity, UUID>{
    
}
