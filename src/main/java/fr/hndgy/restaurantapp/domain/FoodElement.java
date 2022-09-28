package fr.hndgy.restaurantapp.domain;

import java.lang.annotation.ElementType;

import org.springframework.validation.annotation.Validated;

import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class FoodElement extends MenuElement{

    private final Integer preparationTime;

    private FoodElement(double price, String instructions,Integer preparationTime){
        super(price, MenuElementType.FOOD, instructions);
        this.preparationTime = preparationTime;
    }

}
