package com.example.shopinglist.repository;

import com.example.shopinglist.models.RoleOfStatus;
import com.example.shopinglist.models.StatusModel;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<StatusModel, Long> {
    Optional<StatusModel> findByRoleOfStatus(RoleOfStatus role);
}
