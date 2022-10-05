package fr.hndgy.restaurantapp.adapter.in.web;


import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.hndgy.restaurantapp.application.port.in.AddChoiceCommand;
import fr.hndgy.restaurantapp.application.port.in.CreateOrderCommand;
import fr.hndgy.restaurantapp.application.port.in.RemoveChoiceCommand;
import fr.hndgy.restaurantapp.application.service.OrderService;
import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.Table;
import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementId;
import fr.hndgy.restaurantapp.domain.Order.OrderId;
import fr.hndgy.restaurantapp.domain.OrderChoice.OrderChoiceId;
import fr.hndgy.restaurantapp.domain.Table.TableId;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

     private final OrderService orderService;

     @PostMapping("")
     public Order createOrder(@RequestBody CreateOrderDto createOrderDto){
          Table.TableId tableId = TableId.of(UUID.fromString(createOrderDto.getTableId()));
          CreateOrderCommand createOrderCommand = new CreateOrderCommand(tableId);
          return this.orderService.createOrder(createOrderCommand);
     }
     
     @PostMapping("/choice")
     public Order addChoice(@RequestBody AddChoiceDto addOrRemoveChoiceDto){

          OrderId orderId = OrderId.of(UUID.fromString(addOrRemoveChoiceDto.getOrderId()));
          MenuElementId menuElementId = MenuElementId.of(UUID.fromString(addOrRemoveChoiceDto.getMenuElementId()));
          var command = new AddChoiceCommand();
          command.setOrderId(orderId);
          command.setMenuElementId(menuElementId);
          command.setComment(addOrRemoveChoiceDto.getComment());
          return this.orderService.addChoice(command);
     }

     @DeleteMapping("/choice")
     public Order removeChoice(@RequestBody RemoveChoiceDto removeChoiceDto){
          var command = new RemoveChoiceCommand();
          command.setOrderChoiceId(
               OrderChoiceId.of(
                    UUID.fromString(
                         removeChoiceDto.getOrderChoiceId())));
          command.setOrderId(
               OrderId.of(
               UUID.fromString(
                    removeChoiceDto.getOrderId())));
          return this.orderService.removeChoice(command);
     }
}
