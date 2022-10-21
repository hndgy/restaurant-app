package fr.hndgy.restaurantapp.application.service;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import fr.hndgy.restaurantapp.application.events.ChoiceAddedEvent;
import fr.hndgy.restaurantapp.application.events.ChoiceDeletedEvent;
import fr.hndgy.restaurantapp.application.events.OrderCreatedEvent;
import fr.hndgy.restaurantapp.application.events.OrderDeletedEvent;
import fr.hndgy.restaurantapp.application.port.in.AddChoiceCommand;
import fr.hndgy.restaurantapp.application.port.in.CreateOrderCommand;
import fr.hndgy.restaurantapp.application.port.in.OrderService;
import fr.hndgy.restaurantapp.application.port.in.RemoveChoiceCommand;
import fr.hndgy.restaurantapp.application.port.out.MenuElementRepository;
import fr.hndgy.restaurantapp.application.port.out.OrderRepository;
import fr.hndgy.restaurantapp.application.port.out.TableRepository;
import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.OrderChoice;
import fr.hndgy.restaurantapp.domain.Order.OrderId;
import fr.hndgy.restaurantapp.domain.Table;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;
    private final MenuElementRepository menuElementRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Order createOrder(CreateOrderCommand createOrderCommand){
        Table table = tableRepository.getById(createOrderCommand.getTableId());
        Order order = Order.withTableAndNbGuests(table, createOrderCommand.getNbOfGuests());
        order =  orderRepository.createOrder(order);
        applicationEventPublisher.publishEvent(new OrderCreatedEvent(this,order));
        return order;
    }

    @Override
    public OrderChoice addChoice(AddChoiceCommand choiceCommand){
        var order = this.orderRepository.getOrderById(choiceCommand.getOrderId());
        var menuElement = this.menuElementRepository.getById(choiceCommand.getMenuElementId());
        var orderChoice = order.addChoice(menuElement, choiceCommand.getComment(), choiceCommand.getMealCategory());
        this.orderRepository.updateChoices(order);
        this.applicationEventPublisher.publishEvent(new ChoiceAddedEvent(this, orderChoice, order.getOrderId()));
        return orderChoice;
    }

    @Override
    public void removeChoice(RemoveChoiceCommand choiceCommand){
        this.orderRepository.removeChoice(choiceCommand.getOrderChoiceId());
        this.applicationEventPublisher.publishEvent(new ChoiceDeletedEvent(this, choiceCommand));
    }

    @Override
    public Order getOrderById(OrderId orderId) {
        return this.orderRepository.getOrderById(orderId);
    }

    @Override
    public List<Order> getAllPendingOrdersToday() {
        return this.orderRepository.getAllNotEndedOrders();
    }

    @Override
    public List<Order> getAllOrders() {
        return this.orderRepository.getAllOrders();
    }

    @Override
    public void removeOrder(OrderId orderId) {
        this.orderRepository.deleteOrder(orderId);
        this.applicationEventPublisher.publishEvent(new OrderDeletedEvent(this, orderId));
        
    }


}
