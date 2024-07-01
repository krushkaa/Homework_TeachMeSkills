package com.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public Optional<User> getUserById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/create")
    public User createUser(@RequestParam String login) {
        return userService.createUser(login);
    }

    @PutMapping("/change-login")
    public Optional<User> changeUserLogin(@RequestParam Long id, @RequestParam String login) {
        return userService.changeUserLogin(id, login);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }
}
