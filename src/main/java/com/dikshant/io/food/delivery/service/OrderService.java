package com.dikshant.io.food.delivery.service;

import com.dikshant.io.food.delivery.commands.OrderCommandExecutor;
import com.dikshant.io.food.delivery.datastore.OrderData;
import com.dikshant.io.food.delivery.exception.FoodDeliveryException;
import com.dikshant.io.food.delivery.model.Order;
import com.dikshant.io.food.delivery.model.OrderCommandType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dikshant.io.food.delivery.exception.ExceptionType.ORDER_NOT_FOUND;

@Service
public class OrderService {

    private OrderData orderData;
    private List<OrderCommandExecutor> orderCommandExecutorList;

    public OrderService(OrderData orderData, List<OrderCommandExecutor> orderCommandExecutorList) {
        this.orderData = orderData;
        this.orderCommandExecutorList = orderCommandExecutorList;
    }

    public void updateOrder(final Order order, final OrderCommandType orderCommandType) {
        for (OrderCommandExecutor orderCommandExecutor : orderCommandExecutorList) {
            if (orderCommandExecutor.isApplicable(orderCommandType)) {
                orderCommandExecutor.execute(order);
            }
        }
    }

    public Order getOrderById(String orderId) {
        if (!orderData.getOrderById().containsKey(orderId)) {
            throw new FoodDeliveryException(ORDER_NOT_FOUND, "Order not found.");
        }

        return orderData.getOrderById().get(orderId);
    }

    public List<Order> getAllOrders(String userId) {
        List<Order> orders = new ArrayList<>();
        List<String> orderIds = orderData.getOrderIdsByUserId().get(userId);

        if (Optional.of(orderIds).isPresent()) {
            orderIds.forEach(orderId -> orders.add(getOrderById(orderId)));
        }

        return orders;
    }

    public List<Order> getAllOrdersByRestaurantId(String userId, String restaurantId) {
        List<Order> orderList = getAllOrders(userId);

        return orderList.stream().filter(order -> order.getRestaurantId().equals(restaurantId))
                .collect(Collectors.toList());
    }
}
