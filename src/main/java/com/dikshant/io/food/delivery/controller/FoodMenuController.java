package com.dikshant.io.food.delivery.controller;

import com.dikshant.io.food.delivery.model.CuisineType;
import com.dikshant.io.food.delivery.model.FoodMenu;
import com.dikshant.io.food.delivery.model.MealType;
import com.dikshant.io.food.delivery.model.MenuItem;
import com.dikshant.io.food.delivery.service.FoodMenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodMenuController {

    private final FoodMenuService foodMenuService;

    public FoodMenuController(FoodMenuService foodMenuService) {
        this.foodMenuService = foodMenuService;
    }

    @PostMapping("/restaurant/{restaurant_id}/menu")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMenuByRestaurantId(final String menuId, final String restaurantId, final List<MenuItem> menuItems) {
        foodMenuService.addMenuByRestaurantId(menuId, restaurantId, menuItems);
    }

    @GetMapping("/restaurant/{restaurant_id}/menu")
    public ResponseEntity<FoodMenu> getMenuByRestaurantId(final String restaurantId) {
        return new ResponseEntity<>(foodMenuService.getMenuByRestaurantId(restaurantId), HttpStatus.OK);
    }

    @GetMapping("/menu/{menu_id}")
    public ResponseEntity<FoodMenu> getMenuById(final String menuId) {
        return new ResponseEntity<>(foodMenuService.getMenuById(menuId), HttpStatus.OK);
    }

    @PutMapping("/menu/{menu_id}/items")
    public void addMenuItemsByMenuId(final String menuId, final List<MenuItem> menuItems) {
        foodMenuService.addMenuItemsByMenuId(menuId, menuItems);
    }

    @PutMapping("/restaurant/{restaurant_id}/menu/items")
    public void addMenuItemsByRestaurantId(final String restaurantId, final List<MenuItem> menuItems) {
        foodMenuService.addMenuItemsByRestaurantId(restaurantId, menuItems);
    }

    @GetMapping("/restaurant/{restaurant_id}/menu/cuisine/{cuisine_type}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByRestaurantIdAndCuisine(final String restaurantId,
                                                                               final CuisineType cuisineType) {
        return new ResponseEntity<>(foodMenuService.getMenuItemsByRestaurantIdAndCuisine(restaurantId, cuisineType),
                HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurant_id}/menu/meal/{meal_type}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByRestaurantIdAndMealType(final String restaurantId,
                                                                                final MealType mealType) {
        return new ResponseEntity<>(foodMenuService.getMenuItemsByRestaurantIdAndMealType(restaurantId, mealType),
                HttpStatus.OK);
    }
}
