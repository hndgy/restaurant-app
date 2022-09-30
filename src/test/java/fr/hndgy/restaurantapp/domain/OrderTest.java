package fr.hndgy.restaurantapp.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.hndgy.restaurantapp.domain.Table.TableId;

public class OrderTest {

    @Test
    void getTotalPriceWithoutChoice(){
        TableId tableId = new TableId(999l);
        Table table = new Table(tableId,"Test table");
        Order order = Order.withTable(table);

        assertEquals(0, order.getNbElement());
        assertEquals(0., order.getTotalPrice());
    } 
    
    @Test
    void getTotalPriceWithChoices(){
        TableId tableId = new TableId(999l);
        Table table = new Table(tableId,"Test table");
        Order order = Order.withTable(table);
        MenuElement plat1 = MenuElement.foodWithoutId("BoBun", 10.);
        MenuElement plat2 = MenuElement.foodWithoutId("Pho", 15.);
        MenuElement plat3 = MenuElement.foodWithoutId("Banh mi", 5.);
        MenuElement boisson1 = MenuElement.drinkWithoutId("Jus d'orange", 3.);
        MenuElement boisson2 = MenuElement.drinkWithoutId("Biere", 2.);

        order.addChoice(plat1, "sans oignon");
        order.addChoice(plat2, "avec plus de coriandre");
        order.addChoice(plat3);
        order.addChoice(boisson1);
        order.addChoice(boisson2);

        assertEquals(5, order.getNbElement());
        assertEquals(35., order.getTotalPrice());
        assertEquals(5., order.getTotalDrinkPrice());
        assertEquals(30., order.getTotalFoodPrice());
    }
}
