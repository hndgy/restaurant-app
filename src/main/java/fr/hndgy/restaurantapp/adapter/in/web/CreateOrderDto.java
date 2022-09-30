package fr.hndgy.restaurantapp.adapter.in.web;

import java.util.Map;

import lombok.Data;

@Data
public class CreateOrderDto {
    private Long tableId;
    private Map<Long, String> choicesWithComments;
}
