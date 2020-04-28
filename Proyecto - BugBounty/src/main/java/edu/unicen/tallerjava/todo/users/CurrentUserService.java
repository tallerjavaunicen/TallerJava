package edu.unicen.tallerjava.todo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import edu.unicen.tallerjava.todo.log.LogService;

@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CurrentUserService {
	@Autowired
    private
    LogService svc;

	private User current;

	public User getCurrent() {
		if (current != null)
			current = new User();
		return current;
	}

	public void setCurrent(User user) {
		svc.addLog("Log-in de usuario", user);
		current = user;
	}
}
