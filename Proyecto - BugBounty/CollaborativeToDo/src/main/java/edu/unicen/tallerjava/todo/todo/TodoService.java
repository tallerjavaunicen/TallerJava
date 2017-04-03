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
import edu.unicen.tallerjava.todo.users.User;
import edu.unicen.tallerjava.todo.users.UserService;

@Service
public class TodoService {
	@Autowired
	LogService svc;

	@Autowired
	CurrentUserService currentSvc;

	ArrayList<TODO> todos = new ArrayList<>();
	{
		todos.add(new TODO("Hola Mundo!", new User("Usuario de Prueba"), UUID.randomUUID()));
	}

	public ArrayList<TODO> getTodoList() {
		return todos;
	}

	public void addTODO(TODO todo) {
		svc.addLog("Se agregó el todo " + todo.getContent(), todo.getUser());
		todos.add(todo);
	}

	public void delete(UUID id) {
		Iterator<TODO> it = todos.iterator();
		while (it.hasNext()) {
			TODO todo = (TODO) it.next();
			if (todo.getId().equals(id)) {
				svc.addLog("Se borró el todo " + todo.getContent(), currentSvc.getCurrent());
				it.remove();
				return;
			}
		}
	}

	public void borrarMensajesViejos() {
		Date current = new Date();
		Iterator<TODO> it = todos.iterator();
		while (it.hasNext()) {
			TODO todo = (TODO) it.next();
			long diff = current.getTime() - todo.getDate().getTime();
			if (diff > 30 * 1000) {
				svc.addLog("Se borró automáticamente el TODO, " + todo.getContent(), UserService.DEFAULT_USER);
				it.remove();
			}
		}
	}

	@PostConstruct
	public void init() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				borrarMensajesViejos();
			}
		}, 0, 1000);
	}
}
