package com.dikshant.io.food.delivery.exception;

public class FoodDeliveryException extends RuntimeException {
    private ExceptionType exceptionType;
    private String message;

    public FoodDeliveryException(ExceptionType exceptionType, String message) {
        this.exceptionType = exceptionType;
        this.message = message;
    }
}
