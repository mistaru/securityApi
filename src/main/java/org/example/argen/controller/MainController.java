package org.example.argen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }

/*    @GetMapping("/todoEdit")
    public String todoEdit() {
        return "todo/todoEdit";
    }

    @GetMapping("/todoDelete")
    public String todoDelete() {
        return "todo/todoDelete";
    }*/

}
