package fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.hndgy.restaurantapp.application.port.out.OrderChoiceRepository;
import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementId;
import fr.hndgy.restaurantapp.domain.Order.OrderId;
import lombok.RequiredArgsConstructor;
import fr.hndgy.restaurantapp.domain.OrderChoice;

@Repository
@RequiredArgsConstructor
public class OrderChoiceJpaRepository implements OrderChoiceRepository{


    private final OrderChoiceDAO orderChoiceDAO;
    private final OrderChoiceEntityMapper orderChoiceEntityMapper;

    @Override
    public void updateAll(List<OrderChoice> choices) {
        this.orderChoiceDAO.saveAll(choices.stream().map(d -> this.orderChoiceEntityMapper.toEntity(d)).toList());
    }
}
