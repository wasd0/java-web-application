package com.wasd.web.service;

import com.wasd.web.entity.User;
import com.wasd.web.model.UserResponse;
import com.wasd.web.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::buildUserResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponse findById(Integer id) {
        return buildUserResponse(userRepository.findById(id)
                .orElseThrow(NullPointerException::new));
    }

    private UserResponse buildUserResponse(User user) {
        return new UserResponse()
                .setId(user.getId())
                .setName(user.getName())
                .setRegistrationDate(user.getRegistrationDate());
    }
}
