package com.wasd.web.service;

import com.wasd.web.model.user.UserRequest;
import com.wasd.web.model.user.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> findAll();
    UserResponse findById(Integer id);
    UserResponse create(UserRequest userRequest);
    UserResponse update(Integer id, UserRequest userRequest);
    void delete(Integer id);
}
