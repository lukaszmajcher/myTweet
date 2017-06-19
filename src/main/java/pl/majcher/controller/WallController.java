package pl.majcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.majcher.model.Tweet;
import pl.majcher.service.UserService;
import pl.majcher.service.WallService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WallController {

    @Autowired
    private WallService wallService;

    @Autowired
    private UserService userService;


    @RequestMapping
    public List<Tweet> getAll(){
        return wallService.getAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Tweet> add(@Valid @RequestBody Tweet tweet){
        tweet.setAuthor(userService.findOrCreate(tweet.getAuthor().getName()));
        return new ResponseEntity<>(wallService.add(tweet), HttpStatus.OK);
    }
}