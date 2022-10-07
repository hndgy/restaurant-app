package fr.hndgy.restaurantapp.domain;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuCategory {
    private CategoryId categoryId;
    private final String name;

    @Value
    public static class CategoryId{
        private final UUID value;
        public static CategoryId of(UUID uuid){
            return new CategoryId(uuid);
        }
        public static CategoryId generate(){
            return new CategoryId(UUID.randomUUID());
        }
    }
}
