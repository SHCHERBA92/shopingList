package com.example.shopinglist.repository;

import com.example.shopinglist.models.GlobalSpisokModel;
import com.example.shopinglist.models.RoleOfStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GlobalSpisokRepository extends JpaRepository<GlobalSpisokModel, Long> {

}
