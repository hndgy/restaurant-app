package fr.hndgy.restaurantapp.adapter.out.persistance.order;

import org.springframework.objenesis.instantiator.basic.FailingInstantiator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.hndgy.restaurantapp.application.port.out.OrderRepository;
import fr.hndgy.restaurantapp.domain.Order;
import fr.hndgy.restaurantapp.domain.Order.OrderId;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Transactional
public class OrderJpaRepository implements OrderRepository{


    private final OrderEntityMapper orderEntityMapper;
    private final OrderDAO orderDAO;


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
}
