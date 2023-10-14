package ru.madrabit.frankenstein.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.madrabit.frankenstein.dto.LoginDto;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }


}
