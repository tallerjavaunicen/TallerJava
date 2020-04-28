package edu.unicen.tallerjava.todo.log;

import java.util.UUID;

import edu.unicen.tallerjava.todo.users.User;

/*
 * Representa un log de una acción del usuario. El id es un UUID.
 * No se guarda en la base de datos.
 */
public class Log {
    private UUID id;
    private String action;
    private User user;

    public Log(UUID id, String action, User user) {
        super();
        this.id = id;
        this.action = action;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
