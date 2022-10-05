package fr.hndgy.restaurantapp.adapter.out.persistance.order;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderDAO extends JpaRepository<OrderEntity, UUID>{
}
