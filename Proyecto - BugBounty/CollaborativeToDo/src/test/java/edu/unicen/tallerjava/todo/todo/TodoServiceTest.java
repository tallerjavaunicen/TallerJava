package edu.unicen.tallerjava.todo.todo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
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
import edu.unicen.tallerjava.todo.users.CurrentUserService;
import edu.unicen.tallerjava.todo.users.User;
import edu.unicen.tallerjava.todo.users.UserService;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {

	@Mock
	LogService svc;

	@Mock
	CurrentUserService currentSvc;

	@InjectMocks
	TodoService todoService;

	ToDo todo;

	@Before
	public void setup() {
		todo = new ToDo("Nuevo Todo", new User("Ale"), new UUID(0, 0));
	}

	@Test
	public void addTodo() throws InterruptedException {
		todoService.addTODO(todo);
		ArrayList<ToDo> list = todoService.getTodoList();
		
		ToDo theSameTodo = new ToDo("Nuevo Todo", new User("Ale"), new UUID(0, 0));

		assertTrue(list.contains(theSameTodo));
	}

	@Test
	public void addAndDeleteTodo() throws InterruptedException {
		todoService.addTODO(todo);
		todoService.delete(new UUID(0, 0));
		assertEquals(0, todoService.getTodoList().size());
	}

	@Test
	public void addThreeTodos() throws InterruptedException {
		todoService.addTODO(todo);
		todoService.addTODO(todo);
		todoService.addTODO(todo);
		assertEquals(3, todoService.getTodoList().size());
	}

	@Test
	public void timerDelete() throws InterruptedException {
		todoService.addTODO(buildFakeTodo());
		Thread.sleep(5 * 1000);
		todoService.addTODO(buildFakeTodo());
		Thread.sleep(5 * 1000);
		todoService.addTODO(buildFakeTodo());
		Thread.sleep(5 * 1000);

		todoService.deleteOldMessages(11);

		assertEquals(2, todoService.getTodoList().size());
	}

	@Test
	public void timerDeleteConcurrent() throws Throwable {
		for (int i = 0; i < 10000; i++) {			
			todoService.addTODO(buildFakeTodo());
		}

		List<Throwable> exceptions = new ArrayList<>();
		UncaughtExceptionHandler handler = (Thread t, Throwable e) -> {
			synchronized (exceptions) {
				exceptions.add(e);
			}
		};

		Thread t1 = new Thread(() -> {
			todoService.deleteOldMessages(15);
		});
		t1.setUncaughtExceptionHandler(handler);

		Thread t2 = new Thread(() -> {
			todoService.addTODO(todo);
		});
		t2.setUncaughtExceptionHandler(handler);

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		for (Throwable throwable : exceptions) {
			throw throwable;
		}
	}

	private ToDo buildFakeTodo() {
		UUID id = UUID.randomUUID();
		return new ToDo(id.toString(), UserService.DEFAULT_USER, id);
	}

	@After
	public void free() {

	}
}
