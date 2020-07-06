package org.example.argen.controller;

import org.example.argen.entity.User;
import org.example.argen.serviceImp.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import static org.example.argen.constants.Constants.CANT_ADD_NEW_USER;
import static org.example.argen.constants.Constants.USER_ERROR_ADD;

@Controller
public class RegistrationController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid User user, Model model) {

        if (!userServiceImpl.registerUser(user)) {
            model.addAttribute(USER_ERROR_ADD, CANT_ADD_NEW_USER);
            return "registration";
        }
        return "redirect:/login";
    }

}
