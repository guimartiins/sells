package io.github.guimartiins.sells.repository;

import io.github.guimartiins.sells.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public Boolean insert(User user) {
        return true;
    }
}
