package com.dikshant.io.food.delivery.controller;

import com.dikshant.io.food.delivery.exception.FoodDeliveryException;
import com.dikshant.io.food.delivery.model.Restaurant;
import com.dikshant.io.food.delivery.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantController.class);

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/restaurant")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRestaurant(@RequestBody final Restaurant restaurant) {
        restaurantService.addRestaurant(restaurant);
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurant_id}")
    public ResponseEntity<?> getRestaurantById(final String restaurantId) {
        try {
            return new ResponseEntity<>(restaurantService.getRestaurantById(restaurantId), HttpStatus.OK);
        } catch (FoodDeliveryException e) {
            return new ResponseEntity<>("Restaurant not found.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/restaurant/name/{restaurant_name}")
    public ResponseEntity<List<Restaurant>> getRestaurantByName(final String restaurantName) {
        return new ResponseEntity<>(restaurantService.getRestaurantByName(restaurantName), HttpStatus.OK);
    }

    @GetMapping("/restaurant/city/{city}")
    public ResponseEntity<List<Restaurant>> getRestaurantsByCity(final String city) {
        return new ResponseEntity<>(restaurantService.getRestaurantByCity(city), HttpStatus.OK);
    }
}
