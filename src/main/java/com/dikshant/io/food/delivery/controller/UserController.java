package com.dikshant.io.food.delivery.controller;

import com.dikshant.io.food.delivery.model.User;
import com.dikshant.io.food.delivery.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(final User user) {
        userService.addUser(user);
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<User> getUserById(final String userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PutMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(final User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/user/delete/{user_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(final String userId) {
        userService.deleteUser(userId);
    }
}
