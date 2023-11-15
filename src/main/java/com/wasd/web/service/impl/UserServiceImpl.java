package com.wasd.web.service.impl;

import com.wasd.web.entity.User;
import com.wasd.web.model.user.UserRequest;
import com.wasd.web.model.user.UserResponse;
import com.wasd.web.repository.UserRepository;
import com.wasd.web.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::buildResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(Integer id) {
        return buildResponse(userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }

    @Override
    @Transactional
    public UserResponse create(UserRequest userRequest) {
        User user = createUserFromRequest(userRequest);
        userRepository.save(user);
        return buildResponse(user);
    }

    @Override
    @Transactional
    public UserResponse update(Integer id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        user.setName(userRequest.getName());

        userRepository.save(user);

        return buildResponse(user);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    private User createUserFromRequest(UserRequest request) {
        return request == null ? null : new User()
                .setName(request.getName())
                .setRegistrationDate(Calendar.getInstance().getTime());
    }

    private UserResponse buildResponse(User user) {
        return user == null ? null : new UserResponse()
                .setId(user.getId())
                .setName(user.getName())
                .setRegistrationDate(user.getRegistrationDate());
    }
}
