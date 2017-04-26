package edu.unicen.tallerjava.todo.todo;

import static org.junit.Assert.assertEquals;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import edu.unicen.tallerjava.todo.log.LogService;
import edu.unicen.tallerjava.todo.users.CurrentUserService;
import edu.unicen.tallerjava.todo.users.User;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceMultiThreadTest {

	@Mock
	LogService svc;

	@Mock
	CurrentUserService currentSvc;

	@InjectMocks
	TodoService todoService;

	ExecutorService exec;

	@Before
	public void setup() {
		exec = Executors.newFixedThreadPool(50, (Runnable r) -> {return new Thread(r, "Test Thread");});
	}

	@Test
	public void testAddTodo() throws InterruptedException {
		User user = new User("Ale");
		for (int i = 0; i < 1000; i++) {
			exec.execute(() -> {
				todoService.addTODO(new ToDo("Nuevo Todo", user, UUID.randomUUID()));
			});
		}
		exec.shutdown();
		exec.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		assertEquals(todoService.getTodoList().size(), 1000);
	}

	@After
	public void free() {
	}
}
