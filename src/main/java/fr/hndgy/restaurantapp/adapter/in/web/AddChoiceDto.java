package fr.hndgy.restaurantapp.adapter.in.web;

import lombok.Data;

@Data
public class AddChoiceDto {
    private String orderId;
    private String menuElementId;
    private String comment;
}
