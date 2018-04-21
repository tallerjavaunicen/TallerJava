package edu.unicen.tallerjava.todo.users;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unicen.tallerjava.todo.log.LogService;

@Service
public class UserService {
    @Autowired
    private
    LogService logSvc;

    public static final User DEFAULT_USER = new User("Admin", UUID.randomUUID());

    // TreeSet<User> users = new TreeSet<>((User u, User u2) -> u.getId().compareTo(u2.getId()));

    @Autowired
    private
    UserRepository repo;

    public List<User> getUsers() {
        ArrayList<User> arrayList = new ArrayList<>();
        for (User u :
                this.repo.findAll()) {
            arrayList.add(u);
        }
        arrayList.sort(Comparator.comparing(User::getName));
        return arrayList;
    }

    public void addUser(User user) {
        logSvc.addLog("Se agregó el usuario " + user.getName(), user);
        this.repo.save(user);
    }

    public void clearUsers() {
        this.repo.deleteAll();
        logSvc.clear();
    }

    public void setLogSvc(LogService logSvc) {
        this.logSvc = logSvc;
    }

}
