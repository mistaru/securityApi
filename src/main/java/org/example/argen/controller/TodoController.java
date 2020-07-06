package org.example.argen.controller;

import org.example.argen.entity.Todo;
import org.example.argen.entity.User;
import org.example.argen.enums.Status;
import org.example.argen.serviceImp.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static org.example.argen.constants.Constants.*;

@Controller
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoServiceImpl todoService;

    @GetMapping()
    public ModelAndView todoList(@AuthenticationPrincipal User user) {
        return new ModelAndView("todo/todoList")
                .addObject("newTodo", todoService.listTodo(Status.NEW, user))
                .addObject("doingTodo", todoService.listTodo(Status.DOING, user))
                .addObject("doneTodo", todoService.listTodo(Status.DONE, user));
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("todoList")
    public String addNewTodo(@AuthenticationPrincipal User user, @Valid Todo todo) {
        todoService.addNewTodo(user, todo);
        return "redirect:/todo";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("{id}")
    public String todoEditForm(@PathVariable Long id, Model model) {
        Todo todo = todoService.findTodoById(id);
        model.addAttribute("todo", todo);
        return "todo/todoEdit";
    }

    @PreAuthorize("hasAuthority('USER')")
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
        if (!todoService.saveTodo(user, todo, title, description, status)) {
            model.addAttribute(TODO_ERROR_EDIT, CANT_EDIT_TODO);
            return "todo/todoEdit";
        }
        return "redirect:/todo";
    }

    @PreAuthorize("hasAuthority('USER')")
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

}
