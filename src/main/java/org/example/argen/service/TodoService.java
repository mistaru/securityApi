package org.example.argen.service;

import org.example.argen.entity.Todo;
import org.example.argen.entity.User;
import org.example.argen.enums.Status;

public interface TodoService {

    Todo findTodoById(Long id);

    void addNewTodo(User user, Todo todo);

    Iterable<Todo> listTodo(Status status, User user);

    boolean deleteTodo(User user, Todo todo);

}
