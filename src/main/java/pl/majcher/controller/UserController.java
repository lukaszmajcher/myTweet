package pl.majcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.majcher.model.Follow;
import pl.majcher.model.Tweet;
import pl.majcher.model.User;
import pl.majcher.service.FollowService;
import pl.majcher.service.UserService;
import pl.majcher.service.WallService;

import javax.validation.Valid;
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
        return user.map(user1 -> new ResponseEntity<>(user1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}/tweets")
    public ResponseEntity<List<Tweet>> getTweets(@PathVariable int id){
        Optional<User> user = userService.findById(id);
        return user.map(user1 -> new ResponseEntity<>(wallService.findByUser(user1), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}/follows")
    public ResponseEntity<List<User>> getFollowee(@PathVariable int id){
        Optional<User> user = userService.findById(id);
        return user.map(user1 -> new ResponseEntity<>(followService.getFollowee(user1), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}/follows", method = RequestMethod.PUT)
    public ResponseEntity<List<User>> follow(@PathVariable int id, @RequestBody User user){

        Optional<User> follower = userService.findById(id);
        Optional<User> follow = userService.findById(user.getId());

        if (follower == follow)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        if (follower.isPresent() && follow.isPresent()){
            followService.follow(new Follow(follower.get(), follow.get()));
            return new ResponseEntity<>(followService.getFollowee(follower.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}/follows/tweets")
    public ResponseEntity<List<Tweet>> getFolloweeTweets(@PathVariable int id){
        Optional<User> user = userService.findById(id);
        return user.map(user1 -> new ResponseEntity<>(wallService.findByUsers(followService.getFollowee(user1)), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}