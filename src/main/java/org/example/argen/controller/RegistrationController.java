package org.example.argen.controller;

import org.example.argen.entity.User;
import org.example.argen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid User user, Model model) {

        if (!userService.registerUser(user)) {
            model.addAttribute("usernameError", "This user exists, enter a different user name");
            return "registration";
        }
        return "redirect:/login";
    }

}
