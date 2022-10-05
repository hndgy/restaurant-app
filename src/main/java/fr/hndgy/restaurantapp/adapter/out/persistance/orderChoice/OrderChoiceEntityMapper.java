package fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import fr.hndgy.restaurantapp.adapter.out.persistance.common.EntityMapper;
import fr.hndgy.restaurantapp.adapter.out.persistance.menuElement.MenuElementEntity;
import fr.hndgy.restaurantapp.adapter.out.persistance.menuElement.MenuElementEntityMapper;
import fr.hndgy.restaurantapp.adapter.out.persistance.order.OrderEntity;
import fr.hndgy.restaurantapp.adapter.out.persistance.order.OrderEntityMapper;
import fr.hndgy.restaurantapp.domain.MenuElement;
import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.OrderChoice;
import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementId;
import fr.hndgy.restaurantapp.domain.Order.OrderId;
import fr.hndgy.restaurantapp.domain.OrderChoice.OrderChoiceId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderChoiceEntityMapper implements EntityMapper<OrderChoiceEntity, OrderChoice>{

    private final MenuElementEntityMapper menuElementEntityMapper;

    @Override
    public OrderChoiceEntity toEntity(OrderChoice domainObject) {
        OrderChoiceEntity orderChoiceEntity = new OrderChoiceEntity();

        orderChoiceEntity.setId(domainObject.getOrderChoiceId().getValue());

        var menuElement = this.menuElementEntityMapper.toEntity(domainObject.getMenuElement());
        orderChoiceEntity.setMenuElement(menuElement);
        orderChoiceEntity.setCreationDateTime(Timestamp.valueOf( domainObject.getCreationDateTime()));
        orderChoiceEntity.setComment(domainObject.getComment());

        return orderChoiceEntity;
    }

    @Override
    public OrderChoice toDomainObject(OrderChoiceEntity entity) {
        
        OrderChoice domainObj = OrderChoice.of(
            OrderChoiceId.of(entity.getId()),
            this.menuElementEntityMapper.toDomainObject(entity.getMenuElement()),
            entity.getComment(),
            entity.getCreationDateTime().toLocalDateTime()
        );
        return domainObj;
    }
}
