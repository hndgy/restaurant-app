package fr.hndgy.restaurantapp.adapter.in.web.dto;

import java.util.Map;

import lombok.Data;

@Data
public class CreateOrderDto {
    private String tableId;
    private int nbOfGuests;
}
