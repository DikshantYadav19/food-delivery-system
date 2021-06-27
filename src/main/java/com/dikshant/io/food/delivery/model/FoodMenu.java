package com.dikshant.io.food.delivery.model;

import java.util.List;

public class FoodMenu {

    private String id;
    private List<String> restaurantIds;
    private List<MenuItem> menuItemList;

    public FoodMenu(String id, List<String> restaurantIds, List<MenuItem> menuItemList) {
        this.id = id;
        this.restaurantIds = restaurantIds;
        this.menuItemList = menuItemList;
    }

    public String getId() {
        return id;
    }

    public List<String> getRestaurantIds() {
        return restaurantIds;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }
}
