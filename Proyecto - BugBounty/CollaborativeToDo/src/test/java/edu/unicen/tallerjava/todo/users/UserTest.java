package edu.unicen.tallerjava.todo.users;

import static org.junit.Assert.assertEquals;

import java.util.*;

import edu.unicen.tallerjava.todo.todo.ToDo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import edu.unicen.tallerjava.todo.log.LogService;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
    @Mock
    LogService logSvc;

    @Mock
    UserRepository repoUser;

    @InjectMocks
    UserService userService;

    User[] users;

    ArrayList<User> savedUsers;

    @Before
    public void setup() {
        savedUsers = new ArrayList<>();
        users = new User[]{new User("Alejandro", new UUID(0, 0)), new User("Luis", new UUID(1, 0)),
                new User("Juan", new UUID(2, 0)), new User("Carlos", new UUID(3, 0)),
                new User("Marcelo", new UUID(4, 0)), new User("Guillermo", new UUID(5, 0)),
                new User("Sebastián", new UUID(6, 0)), new User("Ulises", new UUID(7, 0)),};
        Mockito.when(repoUser.save(Mockito.any(User.class))).then((el) -> {
            synchronized (savedUsers) {
                savedUsers.add(el.getArgument(0));
            }
            return el.getArgument(0);
        });

        Mockito.when(repoUser.findAll()).then((el) -> savedUsers);
    }

    @Test
    public void addUser() throws InterruptedException {
        for (User user : users) {
            userService.addUser(user);
        }

        assertEquals(users.length, userService.getUsers().size());
    }

    @Test
    public void checkSortedByName() throws InterruptedException {
        for (User user : users) {
            userService.addUser(user);
        }

        List<User> listOfUsersAdded = userService.getUsers();
        List<User> sorted = Arrays.asList(users);

        Collections.sort(sorted, (User u1, User u2) -> u1.getName().compareTo(u2.getName()));
        for (int i = 0; i < users.length; i++) {
            assertEquals(sorted.get(i), listOfUsersAdded.get(i));
        }
    }

    @After
    public void free() {

    }
}
