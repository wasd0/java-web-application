package com.wasd.web.service.impl;

import com.wasd.web.entity.User;
import com.wasd.web.model.user.UserRequest;
import com.wasd.web.model.user.UserResponse;
import com.wasd.web.repository.UserRepository;
import com.wasd.web.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::buildResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(Long id) {
        return buildResponse(userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public UserResponse create(UserRequest userRequest) {
        User user = createUserFromRequest(userRequest);
        userRepository.save(user);
        return buildResponse(user);
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        user.setName(userRequest.getName());

        userRepository.save(user);

        return buildResponse(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private User createUserFromRequest(UserRequest request) {
        return User.builder()
                .name(request.getName())
                .registrationDate(ZonedDateTime.now())
                .build();
    }

    private UserResponse buildResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .registrationDate(user.getRegistrationDate())
                .build();
    }
}
