package org.example.argen.controller;

import org.example.argen.entity.User;
import org.example.argen.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.example.argen.constants.Constants.CANT_ADD_NEW_USER;
import static org.example.argen.constants.Constants.USER_ERROR_ADD;

@Controller
public class RegistrationController {

    private final IUserService IUserService;

    public RegistrationController(@NotNull IUserService IUserService) {
        this.IUserService = IUserService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid User user, Model model) {

        if (!IUserService.registerUser(user)) {
            model.addAttribute(USER_ERROR_ADD, CANT_ADD_NEW_USER);
            return "registration";
        }
        return "redirect:/login";
    }

}
