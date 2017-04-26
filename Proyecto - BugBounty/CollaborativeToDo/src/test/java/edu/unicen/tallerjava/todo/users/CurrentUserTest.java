package edu.unicen.tallerjava.todo.users;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import edu.unicen.tallerjava.todo.log.LogService;

@RunWith(MockitoJUnitRunner.class)
public class CurrentUserTest {
	@Mock
	LogService svc;

	@InjectMocks
	CurrentUserService currentUserSvc;

	@Before
	public void setup() {
	}

	@Test
	public void checkCurrent() throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			// En este caso creamos una instancia separada para crear uno nuevo
			// en cada iteración
			CurrentUserService testSvc = new CurrentUserService();

			final User[] users = new User[2];

			Thread t1 = new Thread(() -> {
				users[0] = testSvc.getCurrent();
			});
			Thread t2 = new Thread(() -> {
				users[1] = testSvc.getCurrent();
			});

			t1.start();
			t2.start();

			t1.join();
			t2.join();

			assertTrue(users[0]==users[1]);
		}
	}

	@Test
	public void checkSetUser() throws InterruptedException {
		User user = new User();
		currentUserSvc.setCurrent(user);
		assertEquals(user, currentUserSvc.getCurrent());
	}

	@Test
	public void checkSetUserConcurrent() throws InterruptedException {
		User user = new User();

		final User[] users = new User[1];

		Thread t1 = new Thread(() -> {
			currentUserSvc.setCurrent(user);
		});
		Thread t2 = new Thread(() -> {
			users[0] = currentUserSvc.getCurrent();
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		assertTrue(users[0]==user);
	}

	@After
	public void free() {

	}
}
