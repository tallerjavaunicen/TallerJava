package edu.unicen.tallerjava.todo.todo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.sun.xml.internal.bind.v2.TODO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import edu.unicen.tallerjava.todo.log.LogService;
import edu.unicen.tallerjava.todo.users.CurrentUserService;
import edu.unicen.tallerjava.todo.users.User;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceMultiThreadTest {

    @Mock
    private TodoRepository repoToDO;

    @Mock
    private LogService svc;

    @Mock
    private CurrentUserService currentSvc;

    @InjectMocks
    private TodoService todoService;

    private ExecutorService exec;

    private final ArrayList<TODO> todos = new ArrayList<>();

    @Before
    public void setup() {
        exec = Executors.newFixedThreadPool(50, (Runnable r) -> {
            return new Thread(r, "Test Thread");
        });
        Mockito.when(repoToDO.save(Mockito.any(ToDo.class))).then((el) -> {
            synchronized (todos) {
                todos.add(el.getArgument(0));
            }
            return el.getArgument(0);
        });

        Mockito.when(repoToDO.findAll()).then((el) -> todos);
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
        assertEquals(1000, todoService.getTodoList().size());
    }

    @After
    public void free() {
    }
}
