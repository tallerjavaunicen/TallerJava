package edu.unicen.tallerjava.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class CollaborativeToDo {

	public static void main(String[] args) {
		SpringApplication.run(CollaborativeToDo.class, args);
	}

	@RequestMapping(value = "/**/{path:[^\\.]*}")
	public String redirect() {
		return "forward:/";
	}

}
