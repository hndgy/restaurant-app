package fr.hndgy.restaurantapp.application.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.hndgy.restaurantapp.application.port.in.TakeOrderCommand;
import fr.hndgy.restaurantapp.application.port.in.TakeOrderUseCase;
import fr.hndgy.restaurantapp.application.port.out.LoadMenuElementPort;
import fr.hndgy.restaurantapp.application.port.out.LoadOrderPort;
import fr.hndgy.restaurantapp.application.port.out.SaveOrderPort;
import fr.hndgy.restaurantapp.domain.MenuElement;
import fr.hndgy.restaurantapp.domain.Order;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TakeOrderService implements TakeOrderUseCase{

    private final LoadMenuElementPort loadMenuElementPort;
    private final SaveOrderPort saveOrderPort;

    @Override
    public Order takeOrder(TakeOrderCommand takeOrderCommand) {
        
        return null;
    }
}
