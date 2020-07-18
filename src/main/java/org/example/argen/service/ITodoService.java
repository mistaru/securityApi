package org.example.argen.service;

import org.example.argen.dto.TodoFilterDto;
import org.example.argen.entity.Todo;
import org.example.argen.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

public interface ITodoService {

    Todo findTodoById(Long id);

    void addNewTodo(User user, Todo todo);

    void deleteTodo(Long id);

    void saveTodo(User user,Todo todo);

    List<Todo> ListIsNotDoneTodo(LocalDate localDate);

    Page<Todo> findAllTodo(Specification<Todo> specification, Pageable pageable);

    Page<Todo> findTodoByAuthor(User user, Pageable pageable);

    Specification<Todo> filterSearch(User user, TodoFilterDto todo);

}
