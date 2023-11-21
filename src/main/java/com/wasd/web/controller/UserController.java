package com.wasd.web.controller;

import com.wasd.web.model.user.UserRequest;
import com.wasd.web.model.user.UserResponse;
import com.wasd.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/allUsers";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        UserResponse userResponse = userService.findById(id);

        ZonedDateTime registrationDateTime = userResponse.getRegistrationDate();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(registrationDateTime.toInstant(), ZoneId.systemDefault());

        String time = String.format("%s:%s", localDateTime.getHour(), registrationDateTime.getMinute());
        String date = String.format("%s.%s", localDateTime.getDayOfMonth(), registrationDateTime.getMonthValue());

        model.addAttribute("registrationTime", String.format("%s - %s", time, date));
        model.addAttribute("user", userResponse);
        return "users/user";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        UserRequest request = new UserRequest();
        model.addAttribute("user", request);
        return "users/createUserForm";
    }

    @GetMapping("{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        UserRequest userRequest = new UserRequest();
        model.addAttribute("user", userRequest);
        model.addAttribute("id", id);
        return "users/editUserForm";
    }

    @PostMapping
    public String create(@ModelAttribute UserRequest userRequest, Model model) {
        model.addAttribute("user", userRequest);
        userService.create(userRequest);
        return "redirect:/users";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute UserRequest request, Model model) {
        userService.update(id, request);
        model.addAttribute("user", request);
        model.addAttribute("id", id);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}