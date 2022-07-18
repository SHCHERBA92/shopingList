package com.example.shopinglist.services;

import com.example.shopinglist.auth_model.AuthUserModel;
import com.example.shopinglist.exceptions.ExceptionNotElements;
import com.example.shopinglist.models.GlobalSpisokModel;
import com.example.shopinglist.repository.GlobalSpisokRepository;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDate;
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

    public List<GlobalSpisokModel> getAllShopSpisok(AuthUserModel userModel) {
        return globalSpisokRepository.findAllByUserModel(userModel);
    }

    public void addNewSpisok(String spisokName, String storeName, LocalDate localDate) {
        if (StringUtils.isEmpty(spisokName) || StringUtils.isEmpty(storeName) || localDate == null) {
            throw new ExceptionNotElements("");
        }
        GlobalSpisokModel globalSpisokModel = new GlobalSpisokModel();
        globalSpisokModel.setNameOfShopList(spisokName);
        globalSpisokModel.setStoreName(storeName);
        globalSpisokModel.setDateTo(localDate);
        globalSpisokRepository.saveAndFlush(globalSpisokModel);
    }

    public GlobalSpisokModel getCurrentSpisok(Long id) {
        return globalSpisokRepository.findById(id)
                .orElseThrow(() -> new ExceptionNotElements("Не смогли найти список"));
    }

    public void deleteCurrentSpisok(Long id) {
        globalSpisokRepository.deleteById(id);
    }

    public List<GlobalSpisokModel> getAllToDaySpisok(){
        LocalDate date = LocalDate.now();
        return globalSpisokRepository.findAllByDateTo(date);
    }
}
