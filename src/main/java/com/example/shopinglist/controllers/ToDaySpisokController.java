package com.example.shopinglist.controllers;

import com.example.shopinglist.auth_model.AuthUserModel;
import com.example.shopinglist.services.AuthUserService;
import com.example.shopinglist.services.GlobalSpisokService;
import com.example.shopinglist.services.ServiceGoods;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("startPageToDay")
@AllArgsConstructor
public class ToDaySpisokController {

    private final GlobalSpisokService globalSpisokService;
    private final AuthUserService authUserService;

    @GetMapping
    public String getToDaySpisok(Model model){
        AuthUserModel userModel = authUserService.getUserFromContext();

        var allShopSpisok = globalSpisokService.getAllToDaySpisok(userModel);

        model.addAttribute("spisokShop", allShopSpisok);
        model.addAttribute("currentUser", userModel);

        return "start_page_shopList_current";
    }
}
