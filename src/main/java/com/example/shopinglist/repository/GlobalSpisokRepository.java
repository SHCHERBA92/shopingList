package com.example.shopinglist.repository;

import com.example.shopinglist.auth_model.AuthUserModel;
import com.example.shopinglist.models.GlobalSpisokModel;
import com.example.shopinglist.models.RoleOfStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GlobalSpisokRepository extends JpaRepository<GlobalSpisokModel, Long> {
    List<GlobalSpisokModel> findAllByDateToAndUserModel(LocalDate date, AuthUserModel userModel);
    List<GlobalSpisokModel> findAllByUserModel(AuthUserModel userModel);
}
