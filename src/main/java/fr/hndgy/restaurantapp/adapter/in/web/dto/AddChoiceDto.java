package fr.hndgy.restaurantapp.adapter.in.web.dto;

import fr.hndgy.restaurantapp.domain.MealCategory;
import lombok.Data;

@Data
public class AddChoiceDto {
    private String menuElementId;
    private String comment;
    private String mealCategory;
}
