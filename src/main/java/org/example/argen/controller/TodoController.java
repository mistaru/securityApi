package org.example.argen.controller;

import org.example.argen.dto.TodoFilterDto;
import org.example.argen.entity.Todo;
import org.example.argen.entity.User;
import org.example.argen.enums.Status;
import org.example.argen.service.ITodoService;
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

    private final ITodoService todoService;

    public TodoController(@NotNull ITodoService todoService) {
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

    @RequestMapping("/preUpdate/{id}")
    public ModelAndView preUpdate(@PathVariable("id")Long id) {
        return new ModelAndView("todo/todoEdit")
                .addObject("todo", todoService.findTodoById(id));
    }

    @PostMapping(value="/update")
    public String update(@AuthenticationPrincipal User user, Todo todo) {
        todoService.saveTodo(user, todo);
        return "redirect:/todo";
    }

    @GetMapping("/delete")
    @Transactional
    public String deleteTodo(Long id) {
        todoService.deleteTodo(id);
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
