package fr.hndgy.restaurantapp.adapter.in.web;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.hndgy.restaurantapp.application.events.ChoiceAddedEvent;
import fr.hndgy.restaurantapp.application.events.ChoiceDeletedEvent;
import fr.hndgy.restaurantapp.application.events.OrderCreatedEvent;
import fr.hndgy.restaurantapp.application.events.OrderDeletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebSocketController {

    private final SimpMessagingTemplate messageTemplate;

    @EventListener
    public void receive(OrderCreatedEvent event){
        log.info("New order created with id : "+ event.getOrder().getOrderId());
        messageTemplate.convertAndSend("/topic/orders", event.getOrder());
    } 
    
    @EventListener
    public void receive(OrderDeletedEvent event){
        log.info("Delete order with id : "+ event.getOrderId());
        messageTemplate.convertAndSend("/topic/ordersDeleted", event.getOrderId());
    }

    @EventListener
    public void receive(ChoiceAddedEvent event){
        log.info("New choice added with id : "+ event.getOrderChoice().getOrderChoiceId());
        messageTemplate.convertAndSend("/topic/choices", event.getOrderChoice());
    } 
    
    @EventListener
    public void receive(ChoiceDeletedEvent event){
        log.info("New choice removed with id : "+ event.getRemoveChoice().getOrderChoiceId().getValue());
        messageTemplate.convertAndSend("/topic/choicesDeleted", event.getRemoveChoice());
    }
}
