package edu.unicen.tallerjava.todo.users;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import edu.unicen.tallerjava.todo.log.LogService;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
	@Mock
	LogService logSvc;
	
	@InjectMocks
	UserService userService;

	User[] users;

	@Before
	public void setup() {
		users = new User[] { new User("Alejandro", new UUID(0, 0)), new User("Luis", new UUID(1, 0)),
				new User("Juan", new UUID(2, 0)), new User("Carlos", new UUID(3, 0)),
				new User("Marcelo", new UUID(4, 0)), new User("Guillermo", new UUID(5, 0)),
				new User("Sebastián", new UUID(6, 0)), new User("Ulises", new UUID(0, 0)), };
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
