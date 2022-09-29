package fr.hndgy.restaurantapp.adapter.out.persistance.order;

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


    private final OrderDAO orderDAO;


    @Override
    public Order createOrder(Order order) {
        return null;
    }

    @Override
    public Order getOrderById(OrderId orderId) {
        return null;
    }
    
}
