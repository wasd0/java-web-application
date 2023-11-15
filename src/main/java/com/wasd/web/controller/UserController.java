package com.wasd.web.controller;

import com.wasd.web.model.UserRequest;
import com.wasd.web.model.UserResponse;
import com.wasd.web.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    
    @PostMapping("/")
    public UserResponse create(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }
    
    @PatchMapping("/{id}")
    public UserResponse update(@PathVariable Integer id, @RequestBody UserRequest request) {
        return userService.update(id, request);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }
}