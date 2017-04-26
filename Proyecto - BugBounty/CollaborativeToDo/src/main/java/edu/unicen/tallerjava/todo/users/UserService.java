package edu.unicen.tallerjava.todo.users;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unicen.tallerjava.todo.log.LogService;

@Service
public class UserService {
	@Autowired
	LogService logSvc;

	public static final User DEFAULT_USER = new User("Admin", UUID.randomUUID());
	TreeSet<User> users = new TreeSet<>((User u, User u2) -> u.getId().compareTo(u2.getId()));

	public List<User> getUsers() {
		return new ArrayList<>(this.users);
	}

	public void addUser(User user) {
		logSvc.addLog("Se agregó el usuario " + user.getName(), user);
		this.users.add(user);
	}

	public void clearUsers() {
		users.clear();
	}

}
