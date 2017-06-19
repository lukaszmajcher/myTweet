package pl.majcher.service;

import pl.majcher.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findOrCreate(String name);
    Optional<User> findById(int in);
    Optional<User> find(User user);
    List<User> getAll();
}
