package edu.unicen.tallerjava.todo.todo;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import edu.unicen.tallerjava.todo.users.User;

/**
 * La entidad ToDo a guardar en la base de datos.
 */
@Entity
public class ToDo {
    @Id
    private UUID id;
    @ManyToOne
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ToDo other = (ToDo) obj;
        if (id == null) {
            return other.id == null;
        } else
            return id.equals(other.id);
    }

}
