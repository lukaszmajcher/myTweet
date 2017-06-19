package pl.majcher.service;

import org.springframework.stereotype.Service;
import pl.majcher.model.Follow;
import pl.majcher.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowServiceImpl implements FollowService {

    private static List<Follow> followRepository = new ArrayList<>();

    @Override
    public void follow(Follow follow) {
        if (followNotExist(follow))
            followRepository.add(follow);
    }

    @Override
    public List<User> getFollowee(User user) {
        return followRepository.stream()
                .filter(follow -> follow.getFollower() == user)
                .map(Follow::getFollowed)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getFollowers(User user) {
        return followRepository.stream()
                .filter(follow -> follow.getFollowed() == user)
                .map(Follow::getFollower)
                .collect(Collectors.toList());
    }

    private boolean followNotExist(Follow foollow){
        return !followRepository.contains(foollow);

    }
}
