package fr.hndgy.restaurantapp.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.hndgy.restaurantapp.application.port.in.CreateMenuElementCommand;
import fr.hndgy.restaurantapp.application.port.in.MenuElementService;
import fr.hndgy.restaurantapp.application.port.out.MenuElementRepository;
import fr.hndgy.restaurantapp.domain.MenuElement;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MenuElementServiceImpl implements MenuElementService{

    private final MenuElementRepository menuElementRepository;

    @Override
    public List<MenuElement> getAllMenuElement() {
        return menuElementRepository.getAll();
    }

    @Override
    public MenuElement create(CreateMenuElementCommand command) {
        MenuElement menuElement = MenuElement.of( command.getName(), command.getPrice(), command.getType());
        return this.menuElementRepository.create(menuElement);
    }
    
}
