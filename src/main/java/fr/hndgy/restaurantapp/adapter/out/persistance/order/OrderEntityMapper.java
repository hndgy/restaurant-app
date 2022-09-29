package fr.hndgy.restaurantapp.adapter.out.persistance.order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import fr.hndgy.restaurantapp.adapter.out.persistance.menuElement.MenuElementEntity;
import fr.hndgy.restaurantapp.adapter.out.persistance.menuElement.MenuElementEntityMapper;
import fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice.OrderChoiceEntity;
import fr.hndgy.restaurantapp.adapter.out.persistance.table.TableEntityMapper;
import fr.hndgy.restaurantapp.domain.MenuElement;
import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.OrderChoice;
import fr.hndgy.restaurantapp.domain.Table;
import fr.hndgy.restaurantapp.domain.Order.OrderId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderEntityMapper {
    
    private final TableEntityMapper tableEntityMapper;
    private final MenuElementEntityMapper menuElementEntityMapper;

    public OrderEntity toEntity(Order order){
        OrderEntity entity = new OrderEntity();
        List<OrderChoiceEntity> choices = new ArrayList<>(); //TODO

        entity.setId(order.getOrderId().getValue());
        entity.setChoices(choices);
        entity.setTable(tableEntityMapper.toEntity(order.getTable()));

        return entity;
    }

    public Order toDomainObject(OrderEntity orderEntity){
        OrderId orderId = new OrderId(orderEntity.getId());
        List<OrderChoice> choices = new ArrayList<>(); //TODO
        Table table = new Table(
            new Table.TableId(orderEntity.getTable().getId()),
            orderEntity.getTable().getName());

        Order order = new Order(orderId,table,choices);
        return order;
    }
}
