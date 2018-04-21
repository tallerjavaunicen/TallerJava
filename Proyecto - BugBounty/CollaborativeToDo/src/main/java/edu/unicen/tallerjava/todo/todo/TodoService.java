package edu.unicen.tallerjava.todo.todo;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unicen.tallerjava.todo.log.LogService;
import edu.unicen.tallerjava.todo.users.CurrentUserService;
import edu.unicen.tallerjava.todo.users.UserService;

@Service
public class TodoService {
    @Autowired
    private
    LogService svc;

    @Autowired
    CurrentUserService currentSvc;

    @Autowired
    private
    TodoRepository repo;

    // ArrayList<ToDo> todos = new ArrayList<>();

    public List<ToDo> getTodoList() {
        Iterable<ToDo> all = repo.findAll();
        ArrayList<ToDo> r = new ArrayList<>();
        for (ToDo t :
                all)
            r.add(t);
        return r;
    }

    public synchronized void addTODO(ToDo todo) {
        svc.addLog("Se agregó el todo " + todo.getContent(), todo.getUser());
        repo.save(todo);
        // todos.add(todo);
    }

    public void delete(UUID id) {
        repo.delete(id);
    }

    public synchronized void deleteOldMessages(int sec) {
        Date current = new Date();
        for (ToDo todo : getTodoList()) {
            long diff = current.getTime() - todo.getDate().getTime();
            if (diff >= sec * 1000) {
                svc.addLog("Se borró automáticamente el todo, " + todo.getContent(), UserService.DEFAULT_USER);
                repo.delete(todo);
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
