package fr.hndgy.restaurantapp.adapter.out.persistance.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import fr.hndgy.restaurantapp.adapter.out.persistance.common.EntityMapper;
import fr.hndgy.restaurantapp.adapter.out.persistance.menuElement.MenuElementEntityMapper;
import fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice.OrderChoiceEntity;
import fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice.OrderChoiceEntityMapper;
import fr.hndgy.restaurantapp.adapter.out.persistance.table.TableEntityMapper;
import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.OrderChoice;
import fr.hndgy.restaurantapp.domain.Table;
import fr.hndgy.restaurantapp.domain.Order.OrderId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderEntityMapper implements EntityMapper<OrderEntity, Order>{
    
    private final TableEntityMapper tableEntityMapper;
    private final OrderChoiceEntityMapper orderChoiceEntityMapper;

    public OrderEntity toEntity(Order order){
        OrderEntity entity = new OrderEntity();
        List<OrderChoiceEntity> choices = new ArrayList<>();

        order.getChoices().forEach(choice -> {
            choices.add(orderChoiceEntityMapper.toEntity(choice));
        });

        if(order.getOrderId() != null){
            entity.setId(order.getOrderId().getValue());
        }

        entity.setChoices(choices);
        entity.setTable(tableEntityMapper.toEntity(order.getTable()));

        return entity;
    }

    public Order toDomainObject(OrderEntity orderEntity){
        OrderId orderId = new OrderId(orderEntity.getId());
        List<OrderChoice> choices = new ArrayList<>();
        orderEntity.getChoices().forEach(choice -> {
            choices.add(orderChoiceEntityMapper.toDomainObject(choice));
        });
        Table table = new Table(
            new Table.TableId(orderEntity.getTable().getId()),
            orderEntity.getTable().getName());

        Order order = new Order(orderId,table,choices);
        return order;
    }
}
