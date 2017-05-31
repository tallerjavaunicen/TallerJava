package edu.unicen.tallerjava.todo.users;

import static org.junit.Assert.assertEquals;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import edu.unicen.tallerjava.todo.log.LogService;

@RunWith(MockitoJUnitRunner.class)
public class UserHeavyTest {
	UserService userService;

	@Rule
	public Timeout globalTimeout = new Timeout(30, TimeUnit.SECONDS);

	@Before
	public void setup() {
		userService = new UserService();
		userService.setLogSvc(new LogService());
	}

	@Test
	public void addUser() throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			// Add 100k users
			for (int j = 0; j < 100000; j++) {
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
