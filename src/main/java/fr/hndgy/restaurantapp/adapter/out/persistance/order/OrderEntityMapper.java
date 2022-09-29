package fr.hndgy.restaurantapp.adapter.out.persistance.order;

import org.springframework.stereotype.Component;

import fr.hndgy.restaurantapp.adapter.out.persistance.table.TableEntityMapper;
import fr.hndgy.restaurantapp.domain.Order;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderEntityMapper {
    
    private final TableEntityMapper tableEntityMapper;

    public OrderEntity toEntity(Order order){
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getOrderId().getValue());

        return entity;
    }
}
