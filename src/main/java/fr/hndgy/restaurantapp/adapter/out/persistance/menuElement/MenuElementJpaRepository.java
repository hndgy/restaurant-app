package fr.hndgy.restaurantapp.adapter.out.persistance.menuElement;

import org.springframework.stereotype.Repository;

import fr.hndgy.restaurantapp.application.port.out.MenuElementRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MenuElementJpaRepository implements MenuElementRepository{
    private final MenuElementDAO menuElementDAO;
}
