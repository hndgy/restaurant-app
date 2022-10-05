package fr.hndgy.restaurantapp.adapter.out.persistance.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.objenesis.instantiator.basic.FailingInstantiator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice.OrderChoiceDAO;
import fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice.OrderChoiceEntity;
import fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice.OrderChoiceEntityMapper;
import fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice.OrderChoiceJpaRepository;
import fr.hndgy.restaurantapp.application.port.out.OrderRepository;
import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.OrderChoice;
import fr.hndgy.restaurantapp.domain.OrderChoice.OrderChoiceId;
import fr.hndgy.restaurantapp.domain.Order.OrderId;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Transactional
public class OrderJpaRepository implements OrderRepository{


    private final OrderEntityMapper orderEntityMapper;
    private final OrderDAO orderDAO;

    private final OrderChoiceEntityMapper orderChoiceEntityMapper;
    private final OrderChoiceDAO orderChoiceDAO;


    @Override
    public Order createOrder(Order order) {
        OrderEntity orderEntity =  this.orderEntityMapper.toEntity(order);
        OrderEntity saved =  orderDAO.save(orderEntity);
        return this.orderEntityMapper.toDomainObject(saved);
    }

    @Override
    public Order getOrderById(OrderId orderId) {
        OrderEntity entity = orderDAO.findById(orderId.getValue()).get();
        return this.orderEntityMapper.toDomainObject(entity);
    }

    @Override
    public void updateChoices(Order order) {
        for(OrderChoice choice : order.getChoices()){
            this.orderChoiceDAO.save(
                this.orderChoiceEntityMapper.toEntity(choice)
            );
        }
    }

    @Override
    public void removeChoice(OrderChoiceId orderChoiceId) {
        this.orderChoiceDAO.deleteById(orderChoiceId.getValue());
    }


}
