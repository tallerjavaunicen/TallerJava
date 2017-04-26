package edu.unicen.tallerjava.todo.todo;

import java.util.Date;
import java.util.UUID;

import edu.unicen.tallerjava.todo.users.User;

public class ToDo {
	UUID id;
	private User user;
	private String content;
	private Date date = new Date();

	public ToDo() {
	}

	public ToDo(String content, User user, UUID id) {
		this.user = user;
		this.content = content;
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
