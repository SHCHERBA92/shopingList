package com.example.shopinglist.controllers;

import com.example.shopinglist.auth_model.AuthUserModel;
import com.example.shopinglist.services.AuthUserService;
import com.example.shopinglist.services.GlobalSpisokService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class StartPage {

    private final GlobalSpisokService globalSpisokService;
    private final AuthUserService authUserService;

    public StartPage(GlobalSpisokService globalSpisokService, AuthUserService authUserService) {
        this.globalSpisokService = globalSpisokService;
        this.authUserService = authUserService;
    }

    @GetMapping("startPage")
    public String startPage(Model model) {
        AuthUserModel userModel = authUserService.getUserFromContext();
        var allShopSpisok = globalSpisokService.getAllShopSpisokByCurrentUser(userModel);
        List<String> tempGoodsForSpisok = new ArrayList<>();

        model.addAttribute("spisokShop", allShopSpisok);
        model.addAttribute("tempGoodsForSpisok", tempGoodsForSpisok);
        model.addAttribute("nickName", userModel.getNickName());

        return "start_page_shopList";
    }

    @PostMapping("createNewList")
    public String createNewList(@RequestParam String listName,
                                @RequestParam String storeName,
                                @RequestParam("dateToShop") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateToShop) {
        LocalDate localDate = LocalDate.ofInstant(dateToShop.toInstant(), ZoneId.systemDefault());
        AuthUserModel userModel = authUserService.getUserFromContext();
        globalSpisokService.addNewSpisok(listName, storeName, localDate, userModel);
        return "redirect:/startPage";
    }

    @PostMapping("deleteCurrentList/{id}/check")
    @Transactional(rollbackOn = RuntimeException.class)
    public String deleteCurrentList(@PathVariable("id") Long id){
        globalSpisokService.deleteCurrentSpisok(id);
        return "redirect:/startPage";
    }

//    private AuthUserModel getUserFromContext(){
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        var email = authentication.getName();
//        return (AuthUserModel) authentication.getPrincipal();
//    }
}
