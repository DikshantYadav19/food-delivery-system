package com.dikshant.io.food.delivery.datastore;

import com.dikshant.io.food.delivery.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RestaurantData {

    private Map<String, Restaurant> restaurantById = new HashMap<>();
    private Map<String, List<String>> restaurantIdsByName = new HashMap<>();
    private Map<String, List<String>> restaurantIdsByCity = new HashMap<>();

    public Map<String, Restaurant> getRestaurantById() {
        return restaurantById;
    }

    public Map<String, List<String>> getRestaurantIdsByName() {
        return restaurantIdsByName;
    }

    public Map<String, List<String>> getRestaurantIdsByCity() {
        return restaurantIdsByCity;
    }
}
