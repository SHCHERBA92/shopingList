package com.example.shopinglist.services;

import com.example.shopinglist.auth_model.AuthUserModel;
import com.example.shopinglist.auth_model.RoleOfUser;
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
        model.setActive(true);
        model.setRoleOfUser(RoleOfUser.USER);
        model.setPassword_(passwordEncoder.encode(model.getPassword_()));
        model.setId(1l);    // TODO : забыл сделать generation на сущности - нужно добавить !
        authUserRepo.save(model);
    }

}
