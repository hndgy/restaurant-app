package fr.hndgy.restaurantapp.adapter.in.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.hndgy.restaurantapp.application.port.in.MenuElementService;
import fr.hndgy.restaurantapp.domain.MenuElement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/menuElement")
public class MenuElementController {
    private final MenuElementService menuElementService;

    @GetMapping
    public List<MenuElement> getAll(){
        return menuElementService.getAllMenuElement();
    }
}
