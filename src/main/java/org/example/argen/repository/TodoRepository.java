package org.example.argen.repository;

import org.example.argen.entity.Todo;
import org.example.argen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, User>, JpaSpecificationExecutor<Todo> {

    Todo findTodoById(Long id);

    void deleteById(Long id);

    @Query(value = "select t from Todo t where t.status <> org.example.argen.enums.Status.DONE and t.closingDate =:date ")
    List<Todo> ListIsNotDoneTodo(@Param("date") LocalDate date);

    List<Todo> findTodoByAuthor(User user);

}
