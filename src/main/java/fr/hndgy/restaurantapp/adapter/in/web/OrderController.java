package fr.hndgy.restaurantapp.adapter.in.web;


import java.util.List;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.hndgy.restaurantapp.application.port.in.AddChoiceCommand;
import fr.hndgy.restaurantapp.application.port.in.CreateOrderCommand;
import fr.hndgy.restaurantapp.application.port.in.OrderService;
import fr.hndgy.restaurantapp.application.port.in.RemoveChoiceCommand;
import fr.hndgy.restaurantapp.application.service.OrderServiceImpl;
import fr.hndgy.restaurantapp.domain.MealCategory;
import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.OrderChoice;
import fr.hndgy.restaurantapp.domain.Table;
import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementId;
import fr.hndgy.restaurantapp.domain.Order.OrderId;
import fr.hndgy.restaurantapp.domain.OrderChoice.OrderChoiceId;
import fr.hndgy.restaurantapp.domain.Table.TableId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

     private final OrderService orderService;

     @PostMapping("")
     public Order createOrder(@RequestBody CreateOrderDto createOrderDto){
          Table.TableId tableId = TableId.of(UUID.fromString(createOrderDto.getTableId()));
          CreateOrderCommand createOrderCommand = new CreateOrderCommand(tableId,createOrderDto.getNbOfGuests());
          return this.orderService.createOrder(createOrderCommand);
     }

     @GetMapping("")
     public List<Order> getListOrder(){
          return this.orderService.getAllOrders();
     }

     @GetMapping("/{orderId}")
     public Order getListOrder(@PathVariable String orderId){
          return this.orderService.getOrderById(
               OrderId.of(UUID.fromString(orderId))
          );
     }


     @PostMapping("/{orderId}/choice")
     public OrderChoice addChoice(@PathVariable String orderId, @RequestBody AddChoiceDto addChoiceDto){

          MenuElementId menuElementId = MenuElementId.of(UUID.fromString(addChoiceDto.getMenuElementId()));
          var command = new AddChoiceCommand();
          command.setOrderId(OrderId.of(UUID.fromString(orderId)));
          command.setMenuElementId(menuElementId);
          command.setComment(addChoiceDto.getComment());
          command.setMealCategory(MealCategory.valueOf(addChoiceDto.getMealCategory()));

          return this.orderService.addChoice(command);
     }

     @DeleteMapping("/{orderId}/choice/{choiceId}")
     public void removeChoice(@PathVariable String orderId, @PathVariable String choiceId){
          var command = new RemoveChoiceCommand();
          command.setOrderChoiceId(
               OrderChoiceId.of(UUID.fromString(choiceId)));

          command.setOrderId(
               OrderId.of(UUID.fromString(orderId)));

          this.orderService.removeChoice(command);
     }
}
