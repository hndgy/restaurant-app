package fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import fr.hndgy.restaurantapp.adapter.out.persistance.menuElement.MenuElementEntity;
import fr.hndgy.restaurantapp.adapter.out.persistance.order.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderChoiceEntity {
    
    @EmbeddedId
    private OrderChoiceKey id;

    @MapsId("orderId")
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @MapsId("menuElementId")
    @ManyToOne
    @JoinColumn(name = "menu_element_id")
    private MenuElementEntity menuElement;

    private String comment;
}
