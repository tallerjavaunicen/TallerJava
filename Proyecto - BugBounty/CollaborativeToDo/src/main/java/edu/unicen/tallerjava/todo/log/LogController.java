package edu.unicen.tallerjava.todo.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LogController {
	@Autowired
    private
    LogService svc;
	
	@RequestMapping(value = "/api/log", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Log> getTodoList() {
		return svc.getLogs();
	}
}
