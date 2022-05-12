package com.example.shopinglist.services;

import com.example.shopinglist.exceptions.ExceptionNotElements;
import com.example.shopinglist.models.RoleOfStatus;
import com.example.shopinglist.repository.GlobalSpisokRepository;
import org.springframework.stereotype.Service;

@Service
public class GlobalSpisokService {
    final GlobalSpisokRepository statusRepository;

    private final String NOT_FIND_ELEMENT = "Элемента нет в базе данных";

    public GlobalSpisokService(GlobalSpisokRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    

}
