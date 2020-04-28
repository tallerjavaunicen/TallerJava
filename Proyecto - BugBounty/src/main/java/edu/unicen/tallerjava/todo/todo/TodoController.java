package edu.unicen.tallerjava.todo.todo;

import java.util.UUID;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TodoController {
    @Autowired
    private TodoService svc;

    @GetMapping(value = "/api/todo", produces = "application/json")
    public @ResponseBody Iterable<ToDo> getTodoList() {
        return svc.getTodoList();
    }

    @DeleteMapping(value = "/api/todo/:id", produces = "application/json")
    public void deleteTodo(@PathParam("id") UUID id) {
        svc.delete(id);
    }

    @PostMapping(value = "/api/todo", produces = "application/json")
    public @ResponseBody ToDo addTodo(@RequestBody ToDo todo) {
        return svc.createNewTodo(todo.getContent());
    }
}
