package com.dikshant.io.food.delivery.datastore;

import com.dikshant.io.food.delivery.model.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderData {

    private Map<String, Order> orderById = new HashMap<>();
    private Map<String, List<String>> orderIdsByUserId = new HashMap<>();

    public Map<String, Order> getOrderById() {
        return orderById;
    }

    public Map<String, List<String>> getOrderIdsByUserId() {
        return orderIdsByUserId;
    }
}
