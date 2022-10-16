package fr.hndgy.restaurantapp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.hndgy.restaurantapp.adapter.out.persistance.menuElement.MenuElementDAO;
import fr.hndgy.restaurantapp.adapter.out.persistance.menuElement.MenuElementEntity;
import fr.hndgy.restaurantapp.adapter.out.persistance.order.OrderDAO;
import fr.hndgy.restaurantapp.adapter.out.persistance.order.OrderEntity;
import fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice.OrderChoiceDAO;
import fr.hndgy.restaurantapp.adapter.out.persistance.orderChoice.OrderChoiceEntity;
import fr.hndgy.restaurantapp.adapter.out.persistance.table.TableDAO;
import fr.hndgy.restaurantapp.adapter.out.persistance.table.TableEntity;
import fr.hndgy.restaurantapp.domain.MealCategory;
import fr.hndgy.restaurantapp.domain.OrderStatus;
import fr.hndgy.restaurantapp.domain.MenuElement.MenuElementType;

@SpringBootApplication
public class RestaurantAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantAppApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(MenuElementDAO menuElementDAO, TableDAO tableDAO, OrderDAO orderDAO, OrderChoiceDAO orderChoiceDAO){

        return args -> {
            var id = UUID.fromString("4ff72f42-ab81-47f0-9288-914d9a751ff1");
            var plat = new MenuElementEntity();
            plat.setId(id);
            plat.setName("bobun");
            plat.setPrice(10.);
            plat.setType(MenuElementType.FOOD);

            plat = menuElementDAO.save(plat);

            System.out.println("PLAT ID = " + plat.getId().toString() );

            var table = new TableEntity();
            table.setId(id);
            table.setName("Test");

            table = tableDAO.save(table);
            System.out.println("TABLE ID = " + table.getId().toString() );

            
            var order = new OrderEntity();
            order.setId(id);
            order.setTable(table);
            order.setCreationDateTime(Timestamp.valueOf(LocalDateTime.now()));
            order.setNbOfGuests(2);
            order.setOrderStatus(OrderStatus.PROCESSING);
            order = orderDAO.save(order);
            System.out.println("ORDER ID = " + order.getId().toString() );

            var choice = new OrderChoiceEntity();
            //choice.setId(new OrderChoiceKey(plat.getId(), order.getId()));
            choice.setMenuElement(plat);
            choice.setCreationDateTime(Timestamp.valueOf(LocalDateTime.now()));
            choice.setOrder(order);
            choice.setComment("test");
            choice.setMealCategory(MealCategory.STARTERS);
            choice.setId(id);

            choice = orderChoiceDAO.save(choice);
            System.out.println("CHOICE ID = " + choice.getId().toString() );
 
            System.out.println("COMMAND LINE RUNNER EXECUTED"); 
        };
        
    }

}
