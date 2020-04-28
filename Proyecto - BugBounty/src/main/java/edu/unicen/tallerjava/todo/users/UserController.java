package edu.unicen.tallerjava.todo.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
class UserController {
    @Autowired
    private UserService svc;

    @Autowired
    private CurrentUserService currentSvc;

    @GetMapping(value = "/api/currentuser", produces = "application/json")
    public @ResponseBody
    User getCurrentUser() {
        return currentSvc.getCurrent();
    }

    @GetMapping(value = "/api/user", produces = "application/json")
    public @ResponseBody
    List<User> getUsers() {
        return svc.getUsers();
    }

    @PostMapping(value = "/api/user", produces = "application/json")
    public @ResponseBody
    User addUser(@RequestBody User userReq) {
        String name = userReq.getName();
        if (name == null || name.isEmpty())
            return new User();
        return svc.login(name);
    }
}
