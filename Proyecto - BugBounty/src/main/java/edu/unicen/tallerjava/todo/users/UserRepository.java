package edu.unicen.tallerjava.todo.users;

import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID>{
    Optional<User> findFirstByOrderByIdDesc();
}
