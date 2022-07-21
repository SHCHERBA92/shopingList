package com.example.shopinglist.controllers;

import com.example.shopinglist.auth_model.AuthUserModel;
import com.example.shopinglist.mail.service.MailSenderService;
import com.example.shopinglist.services.AuthUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/registry")
public class RegistryController {

    private final AuthUserService authUserService;
    private final MailSenderService mailSenderService;


    public RegistryController(AuthUserService authUserService, MailSenderService mailSenderService) {
        this.authUserService = authUserService;
        this.mailSenderService = mailSenderService;
    }

    @GetMapping
    public String getRegistry(Model model) {
        model.addAttribute("ExceptionError", "");
        return "registry_page";
    }

    @PostMapping
    public String postRegistry(@RequestParam String email,
                               @RequestParam String nickName,
                               @RequestParam String pass1,
                               @RequestParam String pass2, Model model) {
            try {
                checkPass(pass1, pass2);
                authUserService.checkEmail(email);

                String code = generateCode();
                var currentUser = createNewUser(email, nickName, pass1, code);
                mailSenderService.sendMail(email, "Подтверждение почты !", code);
                authUserService.addNewUser(currentUser);

                model.addAttribute("email", email);
                return "page_send_mail";
            }
            catch (RuntimeException e){
                model.addAttribute("ExceptionError", e.getMessage());
                return "registry_page";
            }
        }

    @GetMapping("/auth/{code}")
    public String checkAuth(Model model, @PathVariable String code) {
        var currentUser = authUserService.getAuthUserByCodeActivation(code);
        if (currentUser.getActive()) {
            return "redirect:/login";
        } else {
            //TODO: Нужно повесить глобальное исключение на контроллеры и сделать несколько страниц с ошибками
            return null;
        }
    }


    private boolean checkPass(String password, String repeatedPassword) {
        return password.equals(repeatedPassword);
    }

    private AuthUserModel createNewUser(String email, String nickName, String pass1, String codeActive) {

        AuthUserModel authUserModel = new AuthUserModel();

        authUserModel.setEmail(email);
        authUserModel.setNickName(nickName);
        authUserModel.setPassword_(pass1);
        authUserModel.setLocalDateCreated(LocalDate.now());
        authUserModel.setCodeActivation(codeActive);

        return authUserModel;
    }

    private String generateCode() {
        return UUID.randomUUID().toString();
    }
}


// https://www.javadevjournal.com/spring-boot/send-email-using-spring/
