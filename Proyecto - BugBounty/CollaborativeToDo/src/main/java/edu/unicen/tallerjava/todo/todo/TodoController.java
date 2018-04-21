package edu.unicen.tallerjava.todo.todo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.unicen.tallerjava.todo.users.CurrentUserService;

@RestController
class TodoController {
	@Autowired
    private
    TodoService svc;
	@Autowired
    private
    CurrentUserService currentSvc;

	@RequestMapping(value = "/api/todo", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Iterable<ToDo> getTodoList() {
		return svc.getTodoList();
	}

	@RequestMapping(value = "/api/todo", method = RequestMethod.DELETE, produces = "application/json")
	public void deleteTodo(@RequestBody ToDo todo) {
		svc.delete(todo.getId());
	}

	@RequestMapping(value = "/api/todo", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ToDo addTodo(@RequestBody ToDo todo) {
		ToDo nuevo = new ToDo();
		nuevo.setContent(todo.getContent());
		nuevo.setUser(currentSvc.getCurrent());
		nuevo.setId(UUID.randomUUID());
		svc.addTODO(nuevo);
		return nuevo;
	}
}
