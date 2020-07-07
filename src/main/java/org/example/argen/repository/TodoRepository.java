package org.example.argen.repository;

import org.example.argen.entity.Todo;
import org.example.argen.entity.User;
import org.example.argen.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, User> {
    Todo findTodoById(Long id);

    List<Todo> findTodoByStatusAndAuthor(Status status, User user);

    void deleteById(Long id);

    @Query(value = "SELECT u from Todo u where u.status <> org.example.argen.enums.Status.DONE and u.closingDate =:date ")
    List<Todo> ListIsNotDoneTodo(@Param("date") LocalDate date);

}
