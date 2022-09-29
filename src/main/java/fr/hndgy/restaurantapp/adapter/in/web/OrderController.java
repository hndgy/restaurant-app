package fr.hndgy.restaurantapp.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.hndgy.restaurantapp.application.port.in.TakeOrderCommand;
import fr.hndgy.restaurantapp.application.port.in.TakeOrderUseCase;
import fr.hndgy.restaurantapp.domain.Order;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

    private final TakeOrderUseCase takeOrderUseCase;

    @PostMapping("")
    public Order create(TakeOrderDto takeOrderDto){
        return takeOrderUseCase.takeOrder(takeOrderCommand);
    }
}
