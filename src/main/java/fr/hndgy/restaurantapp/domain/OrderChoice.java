package fr.hndgy.restaurantapp.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderChoice {

    private OrderChoiceId orderChoiceId;
    private final MenuElement menuElement;
    private final String comment;
    private final LocalDateTime creationDateTime;
    private final MealCategory mealCategory;

    public static OrderChoice of(MenuElement menuElement, String comment, MealCategory mealCategory){
        return new OrderChoice(OrderChoiceId.generate(), menuElement, comment, LocalDateTime.now(),mealCategory);
    }

    public static OrderChoice of(OrderChoiceId id,MenuElement menuElement, String comment, LocalDateTime creationDateTime,  MealCategory mealCategory){
        return new OrderChoice(id, menuElement, comment, creationDateTime, mealCategory);
    }

    @Value
    public static class OrderChoiceId {
        private final UUID value;

        public static OrderChoiceId of(UUID uuid){
            return new OrderChoiceId(uuid);
        }
        public static OrderChoiceId generate(){
            return new OrderChoiceId(UUID.randomUUID());
        }
    }
}
