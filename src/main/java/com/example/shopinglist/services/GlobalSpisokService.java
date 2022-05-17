package com.example.shopinglist.services;

import com.example.shopinglist.exceptions.ExceptionNotElements;
import com.example.shopinglist.models.GlobalSpisokModel;
import com.example.shopinglist.models.RoleOfStatus;
import com.example.shopinglist.repository.GlobalSpisokRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalSpisokService {
    final GlobalSpisokRepository globalSpisokRepository;

    private final String NOT_FIND_ELEMENT = "Элемента нет в базе данных";

    public GlobalSpisokService(GlobalSpisokRepository statusRepository) {
        this.globalSpisokRepository = statusRepository;
    }

    public List<GlobalSpisokModel> getAllShopSpisok() {
        return globalSpisokRepository.findAll();
    }

}
