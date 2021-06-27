package com.dikshant.io.food.delivery.service;

import com.dikshant.io.food.delivery.datastore.UserData;
import com.dikshant.io.food.delivery.exception.FoodDeliveryException;
import com.dikshant.io.food.delivery.model.User;
import org.springframework.stereotype.Service;

import static com.dikshant.io.food.delivery.exception.ExceptionType.USER_ALREADY_EXISTS;
import static com.dikshant.io.food.delivery.exception.ExceptionType.USER_NOT_FOUND;

@Service
public class UserService {
    private UserData userData;

    public UserService(UserData userData) {
        this.userData = userData;
    }

    public void addUser(final User user) {
        if (userData.getUserById().containsKey(user.getId())) {
            throw new FoodDeliveryException(USER_ALREADY_EXISTS, "Duplicate User");
        }

        userData.getUserById().put(user.getId(), user);
    }

    public User getUserById(final String userId) {
        if (!userData.getUserById().containsKey(userId)) {
            throw new FoodDeliveryException(USER_NOT_FOUND, "User does not exist.");
        }

        return userData.getUserById().get(userId);
    }

    public void deleteUser(final String userId) {
        if (!userData.getUserById().containsKey(userId)) {
            throw new FoodDeliveryException(USER_NOT_FOUND, "User does not exist.");
        }

        userData.getUserById().remove(userId);
    }

    public void updateUser(final User user) {
        if (!userData.getUserById().containsKey(user.getId())) {
            throw new FoodDeliveryException(USER_NOT_FOUND, "User does not exist.");
        }

        userData.getUserById().put(user.getId(), user);
    }
}
