package fr.hndgy.restaurantapp.application.port.out;

public interface RestaurantEventPublisher {
    void publish(String message);
}
