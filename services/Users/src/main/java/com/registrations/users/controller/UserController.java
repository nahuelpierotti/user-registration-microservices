package com.registrations.users.controller;

import com.registrations.users.dto.UserRequestDto;
import com.registrations.users.model.User;
import com.registrations.users.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll().stream().toList();
    }

    @PostMapping("/sign-up")
    public User signUp(@Valid @RequestBody UserRequestDto userRequestDto) {
        return userService.save(userRequestDto);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") long id) {
        return userService.findById(id);
    }
}
