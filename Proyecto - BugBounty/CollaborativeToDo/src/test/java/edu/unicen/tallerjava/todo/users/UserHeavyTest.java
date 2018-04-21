package edu.unicen.tallerjava.todo.users;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import edu.unicen.tallerjava.todo.log.LogService;

@RunWith(MockitoJUnitRunner.class)
public class UserHeavyTest {
    @Mock
    UserRepository repoUser;

    @Mock
    LogService logService;

    @InjectMocks
    UserService userService;

    @Rule
    public Timeout globalTimeout = new Timeout(30, TimeUnit.SECONDS);


    ArrayList<User> savedUsers;

    @Before
    public void setup() {
        savedUsers = new ArrayList<>();
        Mockito.when(repoUser.save(Mockito.any(User.class))).then((el) -> {
            synchronized (savedUsers) {
                savedUsers.add(el.getArgument(0));
            }
            return el.getArgument(0);
        });

        Mockito.doAnswer((el) -> {
            synchronized (savedUsers) {
                savedUsers.clear();
            }
            return null;
        }).when(repoUser).deleteAll();

        Mockito.when(repoUser.findAll()).then((el) -> savedUsers);
    }

    @Test
    public void addUser() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            // Add 100k users
            for (int j = 0; j < 100; j++) {
                UUID id = UUID.randomUUID();
                userService.addUser(new User(id.toString(), id));
            }
            // Remove users
            userService.clearUsers();
        }
        assertEquals(0, userService.getUsers().size());
    }

    @After
    public void free() {

    }
}
