package edu.unicen.tallerjava.todo.todo;

import edu.unicen.tallerjava.todo.log.LogService;
import edu.unicen.tallerjava.todo.users.CurrentUserService;
import edu.unicen.tallerjava.todo.users.User;
import edu.unicen.tallerjava.todo.users.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {

    @Mock
    LogService svc;

    @Mock
    TodoRepository repoToDO;

    @Mock
    CurrentUserService currentSvc;

    @InjectMocks
    TodoService todoService;

    ToDo todo;

    ArrayList<ToDo> todos;

    @Before
    public void setup() {
        todos = new ArrayList<>();
        todo = new ToDo("Nuevo Todo", new User("Ale"), new UUID(0, 0));
        Mockito.when(repoToDO.save(Mockito.any(ToDo.class))).then((el) -> {
            synchronized (todos) {
                todos.add(el.getArgument(0));
            }
            return el.getArgument(0);
        });

        Mockito.doAnswer((el) -> {
            UUID id = el.getArgument(0);
            synchronized (todos) {
                Iterator<ToDo> it = todos.iterator();
                while (it.hasNext()) {
                    ToDo next = it.next();
                    if (next.getId().equals(id)) {
                        it.remove();
                        return null;
                    }
                }
            }
            return null;
        }).when(repoToDO).delete(Mockito.any(UUID.class));

        Mockito.doAnswer((el) -> {
            synchronized (todos) {
                todos.remove(el.getArgument(0));
            }
            return null;
        }).when(repoToDO).delete(Mockito.any(ToDo.class));

        Mockito.when(repoToDO.findAll()).then((el) -> todos);
    }

    @Test
    public void addTodo() throws InterruptedException {
        todoService.addTODO(todo);
        List<ToDo> list = todoService.getTodoList();

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
