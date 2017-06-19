package pl.majcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.majcher.model.Follow;
import pl.majcher.model.Tweet;
import pl.majcher.model.User;
import pl.majcher.service.FollowService;
import pl.majcher.service.UserService;
import pl.majcher.service.WallService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private WallService wallService;

    @Autowired
    private UserService userService;

    @Autowired
    private FollowService followService;


    @RequestMapping
    public ResponseEntity< List<User>> getAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<User> get(@PathVariable int id){
        Optional<User> user = userService.findById(id);
        return user.map(user1 -> new ResponseEntity<>(user1, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}/tweets")
    public ResponseEntity<List<Tweet>> getTweets(@PathVariable int id){
        Optional<User> user = userService.findById(id);
        return user.map(user1 -> new ResponseEntity<>(wallService.findByUser(user1), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}/follows")
    public ResponseEntity<List<User>> getFollowee(@PathVariable int id){
        Optional<User> user = userService.findById(id);
        return user.map(user1 -> new ResponseEntity<>(followService.getFollowee(user1), HttpStatus.MULTI_STATUS)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}/follows/{followID}", method = RequestMethod.POST)
    public ResponseEntity<List<User>> follow(@PathVariable int id, @PathVariable int followID){

        Optional<User> follower = userService.findById(id);
        Optional<User> follow = userService.findById(followID);

        if (follower == follow)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        if (follower.isPresent() && follow.isPresent()){
            followService.follow(new Follow(follower.get(), follow.get()));
            return new ResponseEntity<>(followService.getFollowee(follower.get()), HttpStatus.MULTI_STATUS);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}