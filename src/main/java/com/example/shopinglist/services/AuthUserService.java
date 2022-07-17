package com.example.shopinglist.services;

import com.example.shopinglist.auth_model.AuthUserModel;
import com.example.shopinglist.auth_model.RoleOfUser;
import com.example.shopinglist.exceptions.ExceptionNotElements;
import com.example.shopinglist.repository.AuthUserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {
    private final AuthUserRepo authUserRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthUserService(AuthUserRepo authUserRepo, PasswordEncoder passwordEncoder) {
        this.authUserRepo = authUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void addNewUser(AuthUserModel model){
        model.setActive(false);
        model.setRoleOfUser(RoleOfUser.USER);
        model.setPassword_(passwordEncoder.encode(model.getPassword_()));
        authUserRepo.save(model);
    }

    public AuthUserModel getAuthUserByCodeActivation(String code){
        var user = authUserRepo.findByCodeActivation(code).orElseThrow(() -> new ExceptionNotElements("Код активации не найден"));
        user.setActive(true);
        return user;
    }

}
