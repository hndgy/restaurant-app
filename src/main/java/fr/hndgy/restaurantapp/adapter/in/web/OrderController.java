package fr.hndgy.restaurantapp.adapter.in.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.hndgy.restaurantapp.application.port.in.CreateOrderCommand;
import fr.hndgy.restaurantapp.application.service.OrderService;
import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.Table;
import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementId;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

     private final OrderService orderService;

     @PostMapping("")
     public Order createOrder(CreateOrderDto createOrderDto){
          Table.TableId tableId = new Table.TableId(createOrderDto.getTableId());
          Map<MenuElementId, String> choices = new HashMap();
          createOrderDto.getChoicesWithComments().entrySet().forEach(entry -> {
               choices.put(new MenuElementId(entry.getKey()), entry.getValue());
          });
          CreateOrderCommand createOrderCommand = new CreateOrderCommand(
               choices,
               tableId
          );
          return this.orderService.createOrder(createOrderCommand);
     }
}
