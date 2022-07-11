package com.example.shopinglist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registry")
public class RegistryController {

    @GetMapping
    public String getRegistry(Model model){
        return "registry_page";
    }
}
