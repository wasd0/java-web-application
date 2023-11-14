package com.wasd.web.controller;

import com.wasd.web.model.UserResponse;
import com.wasd.web.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/")
    public List<UserResponse> findAll() {
        return userService.findAll();
    }
    
    @GetMapping("{id}")
    public UserResponse findById(@PathVariable Integer id) {
        return userService.findById(id);
    }
}