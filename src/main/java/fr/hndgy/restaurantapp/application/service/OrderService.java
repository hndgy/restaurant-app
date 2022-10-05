package fr.hndgy.restaurantapp.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.hndgy.restaurantapp.adapter.out.persistance.order.OrderEntity;
import fr.hndgy.restaurantapp.application.port.in.AddChoiceCommand;
import fr.hndgy.restaurantapp.application.port.in.CreateOrderCommand;
import fr.hndgy.restaurantapp.application.port.in.RemoveChoiceCommand;
import fr.hndgy.restaurantapp.application.port.out.MenuElementRepository;
import fr.hndgy.restaurantapp.application.port.out.OrderChoiceRepository;
import fr.hndgy.restaurantapp.application.port.out.OrderRepository;
import fr.hndgy.restaurantapp.application.port.out.TableRepository;
import fr.hndgy.restaurantapp.domain.MenuElement;
import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.Table;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderChoiceRepository orderChoiceRepository;
    private final TableRepository tableRepository;
    private final MenuElementRepository menuElementRepository;

    public Order createOrder(CreateOrderCommand createOrderCommand){
        Table table = tableRepository.getById(createOrderCommand.getTableId());
        Order order = Order.withTable(table);
        return orderRepository.createOrder(order);
    }

    public Order addChoice(AddChoiceCommand choiceCommand){
        var order = this.orderRepository.getOrderById(choiceCommand.getOrderId());
        var menuElement = this.menuElementRepository.getById(choiceCommand.getMenuElementId());
        order.addChoice(menuElement, choiceCommand.getComment());
        this.orderRepository.updateChoices(order);
        return order;
    }

    public Order removeChoice(RemoveChoiceCommand choiceCommand){
        this.orderRepository.removeChoice(choiceCommand.getOrderChoiceId());
        var order = this.orderRepository.getOrderById(choiceCommand.getOrderId());
        return order;


    }
}
