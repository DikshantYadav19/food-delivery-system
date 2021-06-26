package com.dikshant.io.food.delivery.service;

import com.dikshant.io.food.delivery.datastore.RestaurantData;
import com.dikshant.io.food.delivery.exception.FoodDeliveryException;
import com.dikshant.io.food.delivery.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.dikshant.io.food.delivery.exception.ExceptionType.RESTAURANT_ALREADY_EXISTS;
import static com.dikshant.io.food.delivery.exception.ExceptionType.RESTAURANT_NOT_FOUND;

@Service
public class RestaurantService {
    private final RestaurantData restaurantData;

    public RestaurantService(RestaurantData restaurantData) {
        this.restaurantData = restaurantData;
    }

    public void addRestaurant(Restaurant restaurant) {
        if (restaurantData.getRestaurantById().containsKey(restaurant.getId())) {
            throw new FoodDeliveryException(RESTAURANT_ALREADY_EXISTS, "Duplicate Restaurant.");
        }

        restaurantData.getRestaurantById().put(restaurant.getId(), restaurant);

        addToRestaurantIdsByName(restaurant);

        addToRestaurantIdsByCity(restaurant);
    }

    private void addToRestaurantIdsByCity(Restaurant restaurant) {
        List<String> restaurantIdsByCity = restaurantData.getRestaurantIdsByName()
                .getOrDefault(restaurant.getAddress().getCity(), new ArrayList<>());

        restaurantIdsByCity.add(restaurant.getId());
        restaurantData.getRestaurantIdsByCity().put(restaurant.getAddress().getCity(), restaurantIdsByCity);
    }

    private void addToRestaurantIdsByName(Restaurant restaurant) {
        List<String> restaurantIdsByName = restaurantData.getRestaurantIdsByName()
                .getOrDefault(restaurant.getName(), new ArrayList<>());

        restaurantIdsByName.add(restaurant.getId());
        restaurantData.getRestaurantIdsByName().put(restaurant.getName(), restaurantIdsByName);
    }

    public List<Restaurant> getAllRestaurants() {
        return new ArrayList<>(restaurantData.getRestaurantById().values());
    }

    public Restaurant getRestaurantById(String restaurantId) {
        if (!restaurantData.getRestaurantById().containsKey(restaurantId)) {
            throw new FoodDeliveryException(RESTAURANT_NOT_FOUND, "Restaurant does not exist.");
        }
        return restaurantData.getRestaurantById().get(restaurantId);
    }

    public List<Restaurant> getRestaurantByName(String restaurantName) {
        List<Restaurant> restaurants = new ArrayList<>();

        for (String id : restaurantData.getRestaurantIdsByName().get(restaurantName)) {
            restaurants.add(getRestaurantById(id));
        }

        return restaurants;
    }

    public List<Restaurant> getRestaurantByCity(String city) {
        List<Restaurant> restaurants = new ArrayList<>();

        for (String id : restaurantData.getRestaurantIdsByCity().get(city)) {
            restaurants.add(getRestaurantById(id));
        }

        return restaurants;
    }
}
