package pl.majcher.service;

import org.springframework.stereotype.Service;
import pl.majcher.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceImpl implements UserService {

    private AtomicInteger counter = new AtomicInteger();
    private List<User> usersRepository = new ArrayList<>();


    @Override
    public User findOrCreate(String name) {
        return findByName(name).isPresent() ? findByName(name).get() : createUser(name);
    }

    @Override
    public Optional<User> findById(int id) {
        return usersRepository.stream()
                .filter(user -> user.getId() == id)
                .findAny();
    }

    private Optional<User> findByName(String name){
        return usersRepository.stream()
                .filter(user -> user.getName().equals(name))
                .findAny();
    }



    private User createUser(String name) {
        User user = new User();
        user.setId(counter.incrementAndGet());
        user.setName(name);

        usersRepository.add(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return usersRepository;
    }

}
