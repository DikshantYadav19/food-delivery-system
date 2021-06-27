package com.dikshant.io.food.delivery.datastore;

import com.dikshant.io.food.delivery.model.FoodMenu;
import com.dikshant.io.food.delivery.model.MenuItem;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FoodMenuData {
    private Map<String, FoodMenu> foodMenuById = new HashMap<>();
    private Map<String, String> foodMenuIdByRestaurantId = new HashMap<>();
    private Map<String, MenuItem> menuItemsById = new HashMap<>();

    public Map<String, FoodMenu> getFoodMenuById() {
        return foodMenuById;
    }

    public Map<String, String> getFoodMenuIdByRestaurantId() {
        return foodMenuIdByRestaurantId;
    }

    public Map<String, MenuItem> getMenuItemsById() {
        return menuItemsById;
    }
}
