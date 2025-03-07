package com.example.api.controller;

import com.example.api.JwtUtil;
import com.example.api.dto.UserDto;
import com.example.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("all")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{id}")
    public UserDto getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @PutMapping("{id}")
    public UserDto updateUser(@PathVariable long id, @RequestBody UserDto userDto)  {
        return userService.updateUser(userDto);
    }

    @PostMapping("create")
    public UserDto addUser(@Valid @RequestBody UserDto userDto) throws Exception{
        return userService.saveUser(userDto);
    }

    @GetMapping("me")
    public UserDto getMe(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String userId = JwtUtil.extractUserId(token);
        return userService.getUser(Long.parseLong(userId));
    }
}
