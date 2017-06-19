package pl.majcher.service;

import pl.majcher.model.Tweet;
import pl.majcher.model.User;

import java.util.List;

public interface WallService {

    Tweet add(Tweet tweet);
    List<Tweet> findByUser(User user);
    List<Tweet> findByUsers(List<User> users);
    List<Tweet> getAll();
}
