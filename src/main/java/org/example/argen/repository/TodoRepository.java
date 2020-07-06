package org.example.argen.repository;

import org.example.argen.entity.Todo;
import org.example.argen.entity.User;
import org.example.argen.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository <Todo, User> {
    Todo findTodoById(Long id);

    List<Todo> findTodoByStatusAndAuthor(Status status, User user);

    void deleteById(Long id);

}
