package com.example.shopinglist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/login")
@Controller
public class LoginController {

    @GetMapping
    public String getLogin(){
        return "/login";
    }

    @PostMapping
    public String failLogin(){
        return "fail_login";
    }
}
