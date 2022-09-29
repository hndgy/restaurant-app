package fr.hndgy.restaurantapp.adapter.out.persistance.order;

import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderDAO extends JpaRepository<OrderEntity, Long>{
}
