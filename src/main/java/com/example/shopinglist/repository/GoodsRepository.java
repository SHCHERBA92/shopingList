package com.example.shopinglist.repository;

import com.example.shopinglist.models.GoodsModel;
import com.example.shopinglist.models.RoleOfStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoodsRepository extends JpaRepository<GoodsModel, Long> {

    Optional<GoodsModel> findByName(String name);
    List<GoodsModel> findByRoleOfStatus(RoleOfStatus role);
}