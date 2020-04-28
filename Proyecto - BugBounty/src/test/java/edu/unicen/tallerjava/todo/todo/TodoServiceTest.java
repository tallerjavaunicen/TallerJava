package edu.unicen.tallerjava.todo.todo;

import edu.unicen.tallerjava.todo.log.LogService;
import edu.unicen.tallerjava.todo.users.CurrentUserService;
import edu.unicen.tallerjava.todo.users.User;
import edu.unicen.tallerjava.todo.users.UserService;
import edu.unicen.tallerjava.todo.utils.UUIDUtils;
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

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {
    @Mock
    CurrentUserService currentUser;

    @Mock
    LogService logSvc;

    @Mock
    TodoRepository repoToDO;

    @InjectMocks
    TodoService todoService;

    ToDo todo;

    ArrayList<ToDo> todos;

    /**
     * No le den demasiada bola al setup, lo que hace es simular que guardamos y
     * obtenemos cosas de la base de datos, usando una lista de to-dos.
     */
    @Before
    public void setup() {
        todos = new ArrayList<>();
        todo = new ToDo("Nuevo Todo", new User("Ale"), UUIDUtils.simple(0));
        Mockito.when(repoToDO.save(Mockito.any(ToDo.class))).then((el) -> {
            synchronized (todos) {
                todos.add(el.getArgument(0));
            }
            return el.getArgument(0);
        });

        Mockito.doAnswer((el) -> {
            synchronized (todos) {
                todos.remove(el.getArgument(0));
            }
            return null;
        }).when(repoToDO).delete(Mockito.any(ToDo.class));

        Mockito.when(repoToDO.findAll()).then((el) -> todos);
    }

    /**
     * Agrega un ToDo
     */
    @Test
    public void testCreateTodo() {
        String testContent = "Test Content";
        todoService.createNewTodo(testContent);
        List<ToDo> todoList = todoService.getTodoList();

        assertEquals(1, todoList.size());
        assertEquals(testContent, todoList.get(0).getContent());
    }

    /**
     * Agrega 3 todos, uno cada 2 segundos. Borra todos los To-Dos que se hayan
     * agregado hace MÁS de 5 segundos. En este caso, debería borrar solo el primero.
     *
     * @throws InterruptedException
     */
    @Test
    public void testTimerDelete() throws InterruptedException {
        todoService.addTODO(buildFakeTodo());
        Thread.sleep(2 * 1000);
        todoService.addTODO(buildFakeTodo());
        Thread.sleep(2 * 1000);
        todoService.addTODO(buildFakeTodo());
        Thread.sleep(2 * 1000);

        todoService.deleteOldMessages(5);

        assertEquals(2, todoService.getTodoList().size());
    }

    /**
     * Método utilitario. Crea un To-Do fake para hacer pruebas.
     *
     * @return un todo fake
     */
    private ToDo buildFakeTodo() {
        UUID id = UUID.randomUUID();
        return new ToDo(id.toString(), UserService.DEFAULT_USER, id);
    }
}
