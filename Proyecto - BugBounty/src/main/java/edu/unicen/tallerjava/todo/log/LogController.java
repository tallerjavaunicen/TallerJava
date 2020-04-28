package edu.unicen.tallerjava.todo.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
class LogController {
    @Autowired
    private
    LogService svc;

    @GetMapping(value = "/api/log", produces = "application/json")
    public @ResponseBody
    List<Log> getLogs() {
        return svc.getLogs();
    }
}
