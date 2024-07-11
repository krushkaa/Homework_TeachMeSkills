package com.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public String getUser(@RequestParam("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/create")
    public String showCreateUserForm() {
        return "createUser";
    }

    @PostMapping("/create")
    public String createUser(@RequestParam("id") int id, @RequestParam("login") String login) {
        User user = new User();
        user.setId(id);
        user.setLogin(login);
        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/change-login")
    public String showChangeLoginForm() {
        return "changeLogin";
    }

    @PostMapping("/change-login")
    public String changeLogin(@RequestParam("id") int id, @RequestParam("login") String login) {
        userService.changeLogin(id, login);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String showDeleteUserForm() {
        return "deleteUser";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
}
