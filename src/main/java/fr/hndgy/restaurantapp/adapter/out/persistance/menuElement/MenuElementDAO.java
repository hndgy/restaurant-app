package fr.hndgy.restaurantapp.adapter.out.persistance.menuElement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuElementDAO  extends JpaRepository<MenuElementEntity, Long>{
    
}
