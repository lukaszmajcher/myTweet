package pl.majcher.service;

import pl.majcher.model.Follow;
import pl.majcher.model.User;

import java.util.List;

public interface FollowService {

    void follow(Follow follow);
    List<User> getFollowee(User user);
    List<User> getFollowers(User user);

}
