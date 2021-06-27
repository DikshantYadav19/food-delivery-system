package com.dikshant.io.food.delivery.controller;

import com.dikshant.io.food.delivery.model.Order;
import com.dikshant.io.food.delivery.model.OrderCommandType;
import com.dikshant.io.food.delivery.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/user/{user_id}/order/update")
    public void updateOrder(final Order order, final OrderCommandType orderCommandType) {
        orderService.updateOrder(order, orderCommandType);
    }

    @GetMapping("/order/{order_id}")
    public ResponseEntity<Order> getOrderById(final String orderId) {
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

    @GetMapping("/user/{user_id}/order")
    public ResponseEntity<List<Order>> getAllOrders(final String userId) {
        return new ResponseEntity<>(orderService.getAllOrders(userId), HttpStatus.OK);
    }

    @GetMapping("/user/{user_id}/order/{restaurant_id}")
    public ResponseEntity<List<Order>> getAllOrdersByRestaurantId(final String userId, final String restaurantId) {
        return new ResponseEntity<>(orderService.getAllOrdersByRestaurantId(userId, restaurantId), HttpStatus.OK);
    }
}
