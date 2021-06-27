package com.dikshant.io.food.delivery.datastore;

import com.dikshant.io.food.delivery.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserData {

    private Map<String, User> userById = new HashMap<>();

    public Map<String, User> getUserById() {
        return userById;
    }
}
