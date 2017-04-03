package edu.unicen.tallerjava.todo.users;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	public static final User DEFAULT_USER = new User("Admin", UUID.randomUUID());
	List<User> users = new ArrayList<>();

	public List<User> getUsers() {
		return this.users;
	}

	public void addUser(User user) {
		this.users.add(user);
	}

}
