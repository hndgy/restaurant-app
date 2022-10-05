package fr.hndgy.restaurantapp.adapter.out.persistance.menuElement;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuElementEntity {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String name;
    
    private Double price;

    @Enumerated(EnumType.STRING)
    private MenuElementType type;

}
