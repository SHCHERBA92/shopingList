package com.example.shopinglist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("instruction")
public class InstructionController {

    @GetMapping()
    String getInstruction(){
        return "instruction";
    }
}
