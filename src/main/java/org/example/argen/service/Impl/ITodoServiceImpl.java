package org.example.argen.service.Impl;

import org.example.argen.dto.TodoFilterDto;
import org.example.argen.entity.Todo;
import org.example.argen.entity.User;
import org.example.argen.enums.Status;
import org.example.argen.repository.TodoRepository;
import org.example.argen.service.ITodoService;
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
public class ITodoServiceImpl implements ITodoService {

    private final TodoRepository todoRepository;

    public ITodoServiceImpl(@NotNull TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void addNewTodo(User user, Todo todo) {
        todo.setAuthor(user);
        todo.setCreateDate(LocalDate.now());
        todo.setStatus(Status.NEW);
        todoRepository.save(todo);
    }

    public void saveTodo(User user, Todo todo) {
        todo.setCreateDate(LocalDate.now());
        todo.setAuthor(user);
        todoRepository.save(todo);
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

    @Override
    public void deleteTodo(Long id) {
            todoRepository.deleteById(id);

    }

    @Override
    public Todo findTodoById(Long id) {
        return todoRepository.findTodoById(id);
    }

    @Override
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
