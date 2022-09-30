package fr.hndgy.restaurantapp.adapter.out.kafkaPublisher;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import fr.hndgy.restaurantapp.application.port.out.RestaurantEventPublisher;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KafkaPublisher implements RestaurantEventPublisher {
    private final KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void publish(String message) {
        kafkaTemplate.send("newOrders", message);
    }

}
