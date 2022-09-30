package fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice;

import org.springframework.stereotype.Component;

import fr.hndgy.restaurantapp.adapter.out.persistance.common.EntityMapper;
import fr.hndgy.restaurantapp.adapter.out.persistance.menuElement.MenuElementEntityMapper;
import fr.hndgy.restaurantapp.adapter.out.persistance.order.OrderEntityMapper;
import fr.hndgy.restaurantapp.domain.MenuElement;
import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.OrderChoice;
import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementId;
import fr.hndgy.restaurantapp.domain.Order.OrderId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderChoiceEntityMapper implements EntityMapper<OrderChoiceEntity, OrderChoice>{

    private final MenuElementEntityMapper menuElementEntityMapper;

    @Override
    public OrderChoiceEntity toEntity(OrderChoice domainObject) {
        OrderChoiceEntity orderChoiceEntity = new OrderChoiceEntity();

        OrderChoiceKey orderChoiceKey = new OrderChoiceKey();
        orderChoiceKey.setMenuElementId(domainObject.getMenuElement().getMenuElementId().getValue());
        if(domainObject.getOrderId() != null ){
            orderChoiceKey.setOrderId(domainObject.getOrderId().getValue());
        }
        orderChoiceEntity.setId(orderChoiceKey);

        orderChoiceEntity.setComment(domainObject.getComment());
        return orderChoiceEntity;
    }

    @Override
    public OrderChoice toDomainObject(OrderChoiceEntity entity) {
        MenuElement menuElement = this.menuElementEntityMapper.toDomainObject(entity.getMenuElement());
        OrderChoice domainObj = new OrderChoice(
            new OrderId(entity.getOrder().getId()),
            menuElement,
            entity.getComment()
        );
        return domainObj;
    }
    
}
