package com.example.shopinglist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/login")
@Controller
public class LoginController {

    @GetMapping
    public String getLogin(Model model) {
        model.addAttribute("error", false);
        return "/login";
    }

    @GetMapping("/error")
    public String errorLogin(Model model) {
        model.addAttribute("error", true);
        return "/login";
    }
}
