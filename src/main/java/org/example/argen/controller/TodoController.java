package org.example.argen.controller;

import org.example.argen.dto.TodoFilterDto;
import org.example.argen.entity.Todo;
import org.example.argen.entity.User;
import org.example.argen.enums.Status;
import org.example.argen.service.TodoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

import static org.example.argen.constants.Constants.*;

@Controller
@RequestMapping("/todo")
@PreAuthorize("hasAuthority('USER')")
public class TodoController {

    private final TodoService todoService;

    public TodoController(@NotNull TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping()
    public ModelAndView todoList(@AuthenticationPrincipal User user) {
        return new ModelAndView("todo/todoList")
                .addObject("allTodo", todoService.findTodoByAuthor(user));
    }

    @PostMapping("todoList")
    public String addNewTodo(@AuthenticationPrincipal User user, @Valid Todo todo) {
        todoService.addNewTodo(user, todo);
        return "redirect:/todo";
    }

    @GetMapping("{id}")
    public String todoEditForm(@PathVariable Long id, Model model) {
        Todo todo = todoService.findTodoById(id);
        model.addAttribute("todo", todo);
        return "todo/todoEdit";
    }

    @PostMapping()
    public String saveTodo(
            @AuthenticationPrincipal User user,
            @RequestParam("todoId") Long id,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam Status status,
            Model model

    ) {
        Todo todo = todoService.findTodoById(id);
        if (!this.todoService.saveTodo(user, todo, title, description, status)) {
            model.addAttribute(TODO_ERROR_EDIT, CANT_EDIT_TODO);
            return "todo/todoEdit";
        }
        return "redirect:/todo";
    }

    @PostMapping("deleteTodo")
    @Transactional
    public String deleteTodo(
            @AuthenticationPrincipal User user,
            @RequestParam("todoId") Long id,
            Model model
    ) {
        Todo todo = todoService.findTodoById(id);
        if (!todoService.deleteTodo(user, todo)) {
            model.addAttribute(TODO_ERROR_DELETE, CANT_DELETE_TODO);
            return "todo/todoEdit";
        }
        return "redirect:/todo";
    }

    @RequestMapping("/list2")
    public ModelAndView list2(TodoFilterDto todo, @AuthenticationPrincipal User user) {

        List<Todo> todoList = todoService.findAllTodo(todoService.filterSearch(todo));

        List<Todo> todoList2 = new ArrayList<>();

        for (Todo todo1 : todoList) {
            if (todo1.getAuthor().getUsername().equals(user.getUsername()))
                todoList2.add(todo1);
        }

        return new ModelAndView("todo/todoList")
                .addObject("allTodo", todoList2);
    }

}
