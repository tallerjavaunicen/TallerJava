package edu.unicen.tallerjava.todo.log;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import edu.unicen.tallerjava.todo.users.User;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LogServiceTest {
    @InjectMocks
    private LogService svc;

    private String[] events;
    private User user = new User();

    /**
     * Este método se ejecuta SIEMPRE antes de CADA @Test
     */
    @Before
    public void setup() {
        // Genera 100000 eventos desde E0 ... E99999
        events = new String[100000];
        for (int i = 0; i < 100000; i++) {
            events[i] = "E" + i;
        }
    }

    /**
     * Agrega todos los eventos al log y chequea que coincida el tamaño de los
     * eventos con los logs almacenados.
     */
    @Test
    public void addLogs() {
        for (String event : events)
            svc.addLog(event, user);
        assertEquals(events.length, svc.getLogs().size());
    }

    /**
     * Agrega todos los eventos, igual que addLogs, pero esta vez lo hace dividiendo
     * la lista en 2 y agregando cada mitad desde un thread distinto.
     *
     * @throws InterruptedException Excepción de interrupción del Thread. Se agrega
     *                              para evitar poner try-catch.
     */
    @Test
    public void hasAllLogsConcurrent() throws InterruptedException {
        int half = events.length / 2;
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < half; i++) {
                svc.addLog(events[i], user);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int j = half; j < events.length; j++) {
                svc.addLog(events[j], user);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        List<Log> logs = svc.getLogs();

        List<String> actions = logs.stream().map(Log::getAction).collect(Collectors.toList());
        assertEquals(events.length, actions.size());
        assertTrue(actions.containsAll(Arrays.asList(events)));
    }

    @Test
    public void getLogsByUser() {
        User usertest = new User("admin");
        User usertest2 = new User("admin");
        User usertest3 = new User("test");
        svc.addLog("test1", usertest);
        svc.addLog("test2", usertest2);
        svc.addLog("test3", usertest3);
        assertEquals(2, svc.getUserLogs(usertest).size());
        assertEquals(2, svc.getUserLogs(usertest2).size());
        assertEquals(1, svc.getUserLogs(usertest3).size());
    }
}
