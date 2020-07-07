package org.example.argen.service.Imp;

import org.example.argen.entity.Todo;
import org.example.argen.entity.User;
import org.example.argen.enums.Status;
import org.example.argen.repository.TodoRepository;
import org.example.argen.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public void addNewTodo(User user, Todo todo) {
        todo.setAuthor(user);
        todo.setCreateDate(LocalDate.now());
        todo.setStatus(Status.NEW);
        todoRepository.save(todo);

    }

    public Iterable<Todo> listTodo(Status status, User user) {
        return todoRepository.findTodoByStatusAndAuthor(status, user);
    }

    public boolean saveTodo(User user, Todo todo, String title, String description, Status status) {
        if (user.getId().equals(todo.getAuthor().getId())) {
            todo.setCreateDate(LocalDate.now());
            todo.setAuthor(user);
            todo.setTitle(title);
            todo.setDescription(description);
            todo.setStatus(status);
            todoRepository.save(todo);
            return true;
        } else return false;
    }

    @Override
    public List<Todo> ListIsNotDoneTodo(LocalDate date) {
        return todoRepository.ListIsNotDoneTodo(date);
    }

    public boolean deleteTodo(User user, Todo todo) {
        if (user.getId().equals(todo.getAuthor().getId())) {
            todoRepository.deleteById(todo.getId());
            return true;
        } else return false;
    }

    public Todo findTodoById(Long id) {
        return todoRepository.findTodoById(id);
    }

}
