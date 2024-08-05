package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @GetMapping
    public List<UserDto> getUsers() {
        List<UserDto> UserDto = new ArrayList<>();
        UserDto.add(new UserDto(1L, "john_doe", "password123", "john.doe@example.com"));
        UserDto.add(new UserDto(2L, "jane_smith", "password456", "jane.smith@example.com"));
        return UserDto;
    }

    @GetMapping(value = "{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return new UserDto(userId, "john_doe", "password123", "john.doe@example.com");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        System.out.println("User created: " + userDto.getUsername() + ", Email: " + userDto.getEmail());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return new UserDto(userDto.getUserId(), userDto.getUsername(), userDto.getPassword(), userDto.getEmail());
    }

    @DeleteMapping(value = "{userId}")
    public void deleteUser(@PathVariable Long userId) {
        System.out.println("User deleted with ID: " + userId);
    }
}
