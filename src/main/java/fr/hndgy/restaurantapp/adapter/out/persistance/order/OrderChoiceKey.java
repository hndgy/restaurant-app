package fr.hndgy.restaurantapp.adapter.out.persistance.order;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class OrderChoiceKey implements Serializable{
    @Column(name = "menu_element_id")
    private Long menuElementId;

    @Column(name = "order_id")
    private Long orderId;
}
