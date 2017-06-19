package pl.majcher.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.majcher.model.User;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void findOrCreate() throws Exception {

        userService.findOrCreate("Luk");
        userService.findOrCreate("Luk");
        Assert.assertEquals(1, userService.getAll().size());

        userService.findOrCreate("Just");
        Assert.assertEquals(2, userService.getAll().size());
        Assert.assertEquals(1, userService.getAll().get(0).getId());

    }


}