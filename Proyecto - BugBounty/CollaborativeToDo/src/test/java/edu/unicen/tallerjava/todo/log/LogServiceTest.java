package edu.unicen.tallerjava.todo.log;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

@RunWith(MockitoJUnitRunner.class)
public class LogServiceTest {
	@InjectMocks
	private LogService svc;

	private String[] events;
	private User user = new User();

	@Before
	public void setup() {
		events = new String[1000];
		for (int i = 0; i < 1000; i++) {
			events[i] = "E" + i;
		}
	}

	@Test
	public void addLogs() {
		for (String event : events) {
			svc.addLog(event, user);
		}

		assertEquals(events.length, svc.getLogs().size());
	}

	@Test
	public void hasAllLogs() throws InterruptedException {
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

		List<String> actions = logs.stream().map((l) -> l.getAction()).collect(Collectors.toList());
		assertTrue(actions.containsAll(Arrays.asList(events)));
	}

	@After
	public void free() {

	}
}
