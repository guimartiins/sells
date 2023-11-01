package io.github.guimartiins.sells.service;

import io.github.guimartiins.sells.model.User;
import io.github.guimartiins.sells.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public void createUser(User user) {
        validate(user);
        repository.insert(user);
    }

    public void validate(User user) {

    }
}
