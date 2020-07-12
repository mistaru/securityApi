package org.example.argen.service;

import org.example.argen.dto.TodoFilterDto;
import org.example.argen.entity.Todo;
import org.example.argen.entity.User;
import org.example.argen.enums.Status;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

public interface TodoService {

    Todo findTodoById(Long id);

    void addNewTodo(User user, Todo todo);

    boolean deleteTodo(User user, Todo todo);

    boolean saveTodo(User user, Todo todo, String title, String description, Status status);

    List<Todo> ListIsNotDoneTodo(LocalDate localDate);

    List<Todo> findAllTodo(Specification<Todo> specification);

    List<Todo> findTodoByAuthor(User user);

    Specification<Todo> filterSearch(TodoFilterDto todo);

}
