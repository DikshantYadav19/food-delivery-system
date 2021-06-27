package com.dikshant.io.food.delivery.model;

import java.util.List;

public class Order {

    private String id;
    private String userId;
    private String restaurantId;
    private List<MenuItem> menuItemList;
    private OrderStatus orderStatus;

    public Order(String id, String userId, String restaurantId, List<MenuItem> menuItemList) {
        this.id = id;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.menuItemList = menuItemList;
        this.orderStatus = OrderStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void markOrderCancelled() {
        orderStatus = OrderStatus.CANCELLED;
    }

    public void markOrderDelivered() {
        orderStatus = OrderStatus.DELIVERED;
    }

    public void markOrderOutForDelivery() {
        orderStatus = OrderStatus.OUT_FOR_DELIVERY;
    }

    public void markOrderPlaced() {
        orderStatus = OrderStatus.PLACED;
    }

    public void markOrderWaitingForPayment() {
        orderStatus = OrderStatus.WAITING_FOR_PAYMENT;
    }
}
