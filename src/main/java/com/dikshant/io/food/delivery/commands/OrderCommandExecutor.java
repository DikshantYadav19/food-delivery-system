package com.dikshant.io.food.delivery.commands;

import com.dikshant.io.food.delivery.exception.FoodDeliveryException;
import com.dikshant.io.food.delivery.model.Order;
import com.dikshant.io.food.delivery.model.OrderCommandType;

import static com.dikshant.io.food.delivery.exception.ExceptionType.INVALID_ORDER;

public abstract class OrderCommandExecutor {
    public void execute(final Order order) {
        if (!isValid(order)) {
            throw new FoodDeliveryException(INVALID_ORDER, "Order not valid.");
        }

        executeCommand(order);
    }

    public abstract void executeCommand(final Order order);

    public abstract boolean isValid(final Order order);

    public abstract boolean isApplicable(final OrderCommandType orderCommandType);
}
