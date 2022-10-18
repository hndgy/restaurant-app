package fr.hndgy.restaurantapp.adapter.out.persistance.menuElement;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Repository;

import fr.hndgy.restaurantapp.application.port.out.MenuElementRepository;
import fr.hndgy.restaurantapp.domain.MenuElement;
import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementId;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MenuElementJpaRepository implements MenuElementRepository{
    private final MenuElementDAO menuElementDAO;
    private final MenuElementEntityMapper menuElementEntityMapper;

    @Override
    public MenuElement getById(MenuElementId idElement) {
        var entity =  this.menuElementDAO.findById(idElement.getValue()).get();
        return this.menuElementEntityMapper.toDomainObject(entity);
    }

    @Override
    public MenuElement create(MenuElement element) {
        MenuElementEntity entity = this.menuElementEntityMapper.toEntity(element);
        MenuElementEntity saved = this.menuElementDAO.save(entity);

        return this.menuElementEntityMapper.toDomainObject(saved);
    }

    @Override
    public List<MenuElement> getAll() {
        return menuElementDAO.findAll()
                .stream()
                .map(this.menuElementEntityMapper::toDomainObject)
                .toList();
    }
}
