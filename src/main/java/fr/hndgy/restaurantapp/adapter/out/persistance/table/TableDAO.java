package fr.hndgy.restaurantapp.adapter.out.persistance.table;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TableDAO extends JpaRepository<TableEntity, UUID>{
    
    @Query("SELECT t FROM TableEntity t")
    List<TableEntity> findAllDispo();
    
}
