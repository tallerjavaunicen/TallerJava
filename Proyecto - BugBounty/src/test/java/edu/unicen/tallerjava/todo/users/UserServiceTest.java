package edu.unicen.tallerjava.todo.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import edu.unicen.tallerjava.todo.log.LogService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    LogService logService;

    @Mock(stubOnly = true)
    CurrentUserService currentUserSvc;

    @Mock(stubOnly = true)
    UserRepository repoUser;

    @InjectMocks
    UserService userService;

    User[] users;

    ArrayList<User> savedUsers;

    @Before
    public void setup() {
        // Usar el servicio de logs real y no un mock.
        logService = new LogService();
        userService.setLogSvc(this.logService);

        savedUsers = new ArrayList<>();
        users = new User[] { new User("Alejandro", 1), new User("Lucía", 2), new User("Juan", 3), new User("María", 4),
                new User("Marcelo", 5), new User("Antonela", 6), new User("Sebastián", 7), new User("Carolina", 8), };

        Mockito.when(repoUser.save(Mockito.any(User.class))).then((el) -> {
            synchronized (savedUsers) {
                savedUsers.add(el.getArgument(0));
            }
            return el.getArgument(0);
        });

        Mockito.when(repoUser.findAll()).then((el) -> savedUsers);

        Mockito.doAnswer((el) -> {
            savedUsers.clear();
            return null;
        }).when(repoUser).deleteAll();

        Mockito.when(repoUser.findFirstByOrderByIdDesc())
                .then((el) -> Optional.of(savedUsers.get(savedUsers.size() - 1)));

        for (User user : users) {
            userService.addUser(user);
        }

    }

    @Test
    public void testSortedByName() {
        List<User> listOfUsersAdded = userService.getUsers();
        List<User> sorted = Arrays.asList(users);

        Collections.sort(sorted, Comparator.comparing(User::getName));
        for (int i = 0; i < users.length; i++) {
            assertEquals(sorted.get(i), listOfUsersAdded.get(i));
        }
    }

    @Test
    public void testAddNullUser() {
        try {
            userService.addUser(null);
        } catch (IllegalArgumentException e) {
            assertTrue(e != null);
        }
    }

    /**
     * Realiza el login de 3 usuarios y se asegura de que tengan los ids
     * correspondientes.
     */
    @Test
    public void testLoginUser() {
        User a = userService.login("A");
        User b = userService.login("B");
        User c = userService.login("C");

        // Arrancan en nueve porque ya hay 8 usuarios guardados.
        assertTrue(a.getId().equals(9));
        assertTrue(b.getId().equals(10));
        assertTrue(c.getId().equals(11));
    }

    @Test()
    public void testAddUsersAndClear() throws Exception {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 5000; j++) {
                userService.addUser(new User("Test", i * j));
            }
            userService.clearUsers();

            long currentMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            // Chequear que no se pase de aprox. 400MB de RAM.
            if (currentMemory > 1000 * 1000 * 400)
                throw new Exception("Demasiada Memoria: " + currentMemory / 1024 + " mb");
        }
    }
}
