package com.ufpb.SwiftManga.src.controllers;


import com.ufpb.SwiftManga.src.dto.UserDto;
import com.ufpb.SwiftManga.src.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="/users")
    public List<UserDto> listUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path="/user/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping(path="/createUser")
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping(path="/updateUser")
    public UserDto updateUser(@RequestBody @Valid UserDto user) {
        return userService.updateUser(user.getId(), user);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path="deleteUser/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
