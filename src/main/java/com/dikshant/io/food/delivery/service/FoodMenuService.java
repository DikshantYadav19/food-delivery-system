package com.dikshant.io.food.delivery.service;

import com.dikshant.io.food.delivery.datastore.FoodMenuData;
import com.dikshant.io.food.delivery.exception.FoodDeliveryException;
import com.dikshant.io.food.delivery.model.CuisineType;
import com.dikshant.io.food.delivery.model.FoodMenu;
import com.dikshant.io.food.delivery.model.MealType;
import com.dikshant.io.food.delivery.model.MenuItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.dikshant.io.food.delivery.exception.ExceptionType.MENU_NOT_FOUND;
import static com.dikshant.io.food.delivery.exception.ExceptionType.RESTAURANT_ALREADY_EXISTS;

@Service
public class FoodMenuService {
    private FoodMenuData foodMenuData;

    public void addMenuByRestaurantId(final String menuId, final String restaurantId, final List<MenuItem> menuItems) {
        if (foodMenuData.getFoodMenuIdByRestaurantId().containsKey(restaurantId)) {
            throw new FoodDeliveryException(RESTAURANT_ALREADY_EXISTS, "Menu already exists for this restaurant.");
        }

        FoodMenu foodMenu = foodMenuData.getFoodMenuById().getOrDefault(menuId,
                new FoodMenu(menuId, new ArrayList<>(), menuItems));

        foodMenu.getRestaurantIds().add(restaurantId);
        foodMenuData.getFoodMenuById().put(menuId, foodMenu);
        foodMenuData.getFoodMenuIdByRestaurantId().put(restaurantId, menuId);
        for (MenuItem menuItem : menuItems) {
            foodMenuData.getMenuItemsById().put(menuItem.getId(), menuItem);
        }
    }

    public FoodMenu getMenuById(final String menuId) {
        if (!foodMenuData.getFoodMenuById().containsKey(menuId)) {
            throw new FoodDeliveryException(MENU_NOT_FOUND, "Menu does not exist.");
        }

        return foodMenuData.getFoodMenuById().get(menuId);
    }

    public FoodMenu getMenuByRestaurantId(final String restaurantId) {
        if (!foodMenuData.getFoodMenuIdByRestaurantId().containsKey(restaurantId)) {
            throw new FoodDeliveryException(MENU_NOT_FOUND, "Menu does not exist.");
        }
        return getMenuById(foodMenuData.getFoodMenuIdByRestaurantId().get(restaurantId));
    }

    public void addMenuItemsByRestaurantId(final String restaurantId, final List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems) {
            foodMenuData.getMenuItemsById().put(menuItem.getId(), menuItem);
        }

        getMenuByRestaurantId(restaurantId).getMenuItemList().addAll(menuItems);
    }

    public void addMenuItemsByMenuId(final String menuId, final List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems) {
            foodMenuData.getMenuItemsById().put(menuItem.getId(), menuItem);
        }

        getMenuById(menuId).getMenuItemList().addAll(menuItems);
    }

    public List<MenuItem> getMenuItemsByRestaurantIdAndCuisine(final String restaurantId, final CuisineType cuisineType) {
        FoodMenu menuByRestaurantId = getMenuByRestaurantId(restaurantId);

        return menuByRestaurantId.getMenuItemList().stream()
                .filter(menuItem -> menuItem.getCuisineType().equals(cuisineType))
                .collect(Collectors.toList());
    }

    public List<MenuItem> getMenuItemsByRestaurantIdAndMealType(final String restaurantId, final MealType mealType) {
        FoodMenu menuByRestaurantId = getMenuByRestaurantId(restaurantId);

        return menuByRestaurantId.getMenuItemList().stream()
                .filter(menuItem -> menuItem.getMealType().equals(mealType))
                .collect(Collectors.toList());
    }
}
