package fr.hndgy.restaurantapp.adapter.out.persistance.order;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import fr.hndgy.restaurantapp.adapter.out.persistance.table.TableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TableEntity table;

    @OneToMany(mappedBy = "order")
    private List<OrderChoiceEntity> choices;

}