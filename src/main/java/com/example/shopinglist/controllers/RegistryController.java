package com.example.shopinglist.controllers;

import com.example.shopinglist.auth_model.AuthUserModel;
import com.example.shopinglist.services.AuthUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/registry")
public class RegistryController {

    private final AuthUserService authUserService;

    public RegistryController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @GetMapping
    public String getRegistry(Model model){
        return "registry_page";
    }

    @PostMapping
    public String postRegistry(@RequestParam String email,
                               @RequestParam String nickName,
                               @RequestParam String pass1,
                               @RequestParam String pass2, Model model){
        if (checkPass(pass1, pass2)){
            var currentUser = createNewUser(email,nickName,pass1);
            authUserService.addNewUser(currentUser);
            return "redirect:/login";
        }else {
            return "redirect:/registry";
        }

    }

    private boolean checkPass(String password, String repeatedPassword) {
        return password.equals(repeatedPassword);
    }

    private AuthUserModel createNewUser(String email, String nickName, String pass1) {
        AuthUserModel authUserModel = new AuthUserModel();
        authUserModel.setEmail(email);
        authUserModel.setNickName(nickName);
        authUserModel.setPassword_(pass1);
        authUserModel.setLocalDateCreated(LocalDate.now());
        return authUserModel;
    }
}



// https://www.javadevjournal.com/spring-boot/send-email-using-spring/
