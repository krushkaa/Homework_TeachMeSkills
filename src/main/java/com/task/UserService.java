package com.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(String login) {
        User user = new User();
        user.setLogin(login);
        return userRepository.save(user);
    }

    public Optional<User> changeUserLogin(Long id, String login) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setLogin(login);
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
