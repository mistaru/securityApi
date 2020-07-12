package org.example.argen.service.Impl;

import org.example.argen.dto.TodoFilterDto;
import org.example.argen.entity.Todo;
import org.example.argen.entity.User;
import org.example.argen.enums.Status;
import org.example.argen.repository.TodoRepository;
import org.example.argen.service.TodoService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(@NotNull TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void addNewTodo(User user, Todo todo) {
        todo.setAuthor(user);
        todo.setCreateDate(LocalDate.now());
        todo.setStatus(Status.NEW);
        todoRepository.save(todo);
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

    @Override
    public List<Todo> findAllTodo(Specification<Todo> specification) {
        return todoRepository.findAll(specification);
    }

    @Override
    public List<Todo> findTodoByAuthor(User user) {
        return todoRepository.findTodoByAuthor(user);
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

    public Specification<Todo> filterSearch(TodoFilterDto todo) {
        return new Specification<Todo>() {

            private static final long serialVersionUID = -4082638366492629927L;

            @Override
            public Predicate toPredicate(Root<Todo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (todo != null) {
                    if (todo.getTitle() != null && !"".equals(todo.getTitle())) {
                        predicate.getExpressions().add(cb.like(root.get("title"), "%" + todo.getTitle() + "%"));
                    }

                    if (todo.getStatus() != null && !"".equals((todo.getStatus().name()))) {
                        predicate.getExpressions().add(cb.equal(root.get("status"), todo.getStatus()));
                    }

                    if (todo.getTo() != null) {
                        predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("closingDate"), todo.getTo()));
                    }

                    if (todo.getFrom() != null) {
                        predicate.getExpressions().add(cb.greaterThan(root.get("closingDate"), todo.getFrom()));
                    }
                }

                return predicate;
            }
        };
    }

}
