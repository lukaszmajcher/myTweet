package pl.majcher.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.majcher.model.Tweet;
import pl.majcher.model.User;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class WallServiceImplTest {

    @InjectMocks
    private WallServiceImpl wallService;

    @Test
    public void add() throws Exception {
        User author1 = new User("Luk");
        Tweet tweet = new Tweet("AAAAA", author1);
        wallService.add(tweet);
    }

    @Test
    public void findByUser() throws Exception {

    }

}