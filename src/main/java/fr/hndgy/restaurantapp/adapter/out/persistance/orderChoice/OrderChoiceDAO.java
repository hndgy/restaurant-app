package fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderChoiceDAO extends JpaRepository<OrderChoiceEntity, UUID>{
}
