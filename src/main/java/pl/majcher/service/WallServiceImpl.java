package pl.majcher.service;

import org.springframework.stereotype.Service;
import pl.majcher.model.Tweet;
import pl.majcher.model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class WallServiceImpl implements WallService {

    private AtomicInteger counter = new AtomicInteger();
    private List<Tweet> tweetsRepository = new ArrayList<>();

    @Override
    public Tweet add(Tweet tweet) {
        tweet.setId(counter.incrementAndGet());
        tweetsRepository.add(tweet);
        return tweet;
    }

    @Override
    public List<Tweet> findByUser(User user) {
        return tweetsRepository.stream()
                .sorted(Comparator.reverseOrder())
                .filter(tweet -> tweet.getAuthor() == user)
                .collect(Collectors.toList());
    }
    @Override
    public List<Tweet> findByUsers(List<User> users){
        List<Tweet> usersTweets = new ArrayList<>();
        users.forEach(u -> usersTweets.addAll(findByUser(u)));
        return usersTweets.stream()
                .sorted(Comparator.comparingInt(Tweet::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Tweet> getAll() {
        return tweetsRepository;
    }
}
