package com.example.shopinglist.repository;

import com.example.shopinglist.auth_model.AuthUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AuthUserRepo extends JpaRepository<AuthUserModel, Long> {

    Optional<AuthUserModel> findByEmail(String eMail);

    Optional<AuthUserModel> findByNickName(String nickName);

    Optional<AuthUserModel> findByCodeActivation(String code);
}
