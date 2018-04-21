package edu.unicen.tallerjava.todo.users;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class UserController {
	@Autowired
    private
    UserService svc;

	@Autowired
    private
    CurrentUserService currentSvc;

	@RequestMapping(value = "/api/currentuser", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody User getCurrentUser() {
		return currentSvc.getCurrent();
	}

	@RequestMapping(value = "/api/user", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<User> getUsers() {
		return svc.getUsers();
	}

	@RequestMapping(value = "/api/user", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody User addUser(@RequestBody User userReq) {
		String name = userReq.getName();
		if (name == null || name.isEmpty())
			return new User();
		User user = new User(name, UUID.randomUUID());
		currentSvc.setCurrent(user);
		svc.addUser(user);
		return user;
	}
}
