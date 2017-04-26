package edu.unicen.tallerjava.todo.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unicen.tallerjava.todo.log.LogService;
import edu.unicen.tallerjava.todo.users.CurrentUserService;
import edu.unicen.tallerjava.todo.users.UserService;

@Service
public class TodoService {
	@Autowired
	LogService svc;

	@Autowired
	CurrentUserService currentSvc;

	ArrayList<ToDo> todos = new ArrayList<>();

	public ArrayList<ToDo> getTodoList() {
		return todos;
	}

	public void addTODO(ToDo todo) {
		svc.addLog("Se agregó el todo " + todo.getContent(), todo.getUser());
		todos.add(todo);
		todos.add(todo);
	}

	public void delete(UUID id) {
		Iterator<ToDo> it = todos.iterator();
		while (it.hasNext()) {
			ToDo todo = (ToDo) it.next();
			if (todo.getId() == id) {
				svc.addLog("Se borró el todo " + todo.getContent(), currentSvc.getCurrent());
				it.remove();
				return;
			}
		}
	}

	public void deleteOldMessages(int sec) {
		Date current = new Date();
		Iterator<ToDo> it = todos.iterator();
		while (it.hasNext()) {
			ToDo todo = (ToDo) it.next();
			long diff = current.getTime() - todo.getDate().getTime();
			if (diff < sec * 1000) {
				svc.addLog("Se borró automáticamente el TODO, " + todo.getContent(), UserService.DEFAULT_USER);
				it.remove();
			}
		}
	}

	@PostConstruct
	public void init() {
		Timer timer = new Timer("Delete Old Messages Thread");
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				deleteOldMessages(30);
			}
		}, 0, 1000);
	}
}
