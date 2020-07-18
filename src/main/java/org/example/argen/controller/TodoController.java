package org.example.argen.controller;

import org.example.argen.dto.TodoFilterDto;
import org.example.argen.entity.Todo;
import org.example.argen.entity.User;
import org.example.argen.service.ITodoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("/todo")
@PreAuthorize("hasAuthority('USER')")
public class TodoController {

    private final ITodoService todoService;

    public TodoController(@NotNull ITodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/main")
    public String todoList(@AuthenticationPrincipal User user, Model model,
                           @PageableDefault(size = 5,sort = {"id"},
                                   direction = Sort.Direction.DESC)
                                   Pageable pageable) {

        model.addAttribute("page", todoService.findTodoByAuthor(user, pageable));
        model.addAttribute("url", "/todo/main");
        return "todo/todoList";
    }

    @RequestMapping("/list2")
    public String list2(TodoFilterDto todo, @AuthenticationPrincipal User user,
                        @PageableDefault(size = 5, sort = { "id" },
                                direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        Page<Todo> page = todoService.findAllTodo(todoService.filterSearch(user, todo), pageable);

        model.addAttribute("page", page);
        model.addAttribute("url", "/todo/list2");
        return "todo/todoList";
    }

    @PostMapping("todoList")
    public String addNewTodo(@AuthenticationPrincipal User user, @Valid Todo todo) {
        todoService.addNewTodo(user, todo);
        return "redirect:/todo/main";
    }

    @RequestMapping("/preUpdate/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Long id) {
        return new ModelAndView("todo/todoEdit")
                .addObject("todo", todoService.findTodoById(id));
    }

    @PostMapping(value = "/update")
    public String update(@AuthenticationPrincipal User user, Todo todo) {
        todoService.saveTodo(user, todo);
        return "redirect:/todo/main";
    }

    @GetMapping("/delete")
    public String deleteTodo(Long id) {
        todoService.deleteTodo(id);
        return "redirect:/todo/main";
    }

}
