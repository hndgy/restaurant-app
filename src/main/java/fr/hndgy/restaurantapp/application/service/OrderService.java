package fr.hndgy.restaurantapp.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.hndgy.restaurantapp.application.port.in.CreateOrderCommand;
import fr.hndgy.restaurantapp.application.port.out.MenuElementRepository;
import fr.hndgy.restaurantapp.application.port.out.OrderRepository;
import fr.hndgy.restaurantapp.application.port.out.TableRepository;
import fr.hndgy.restaurantapp.domain.MenuElement;
import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.Table;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private MenuElementRepository menuElementRepository;
    private TableRepository tableRepository;

    public Order createOrder(CreateOrderCommand createOrderCommand){
        Table table = tableRepository.getById(createOrderCommand.getTableId());
        Order order = Order.withTable(table);
        createOrderCommand.getMenuElementsAndComment().entrySet().forEach((choice) -> {
            MenuElement element = menuElementRepository.getById(choice.getKey());
            order.addChoice(element, choice.getValue());
        });
        return orderRepository.createOrder(order);
    }
}
