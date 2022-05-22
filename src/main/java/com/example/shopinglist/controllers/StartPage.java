package com.example.shopinglist.controllers;

import com.example.shopinglist.services.GlobalSpisokService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class StartPage {

    private final GlobalSpisokService globalSpisokService;

    @GetMapping("startPage")
    public String startPage(Model model){
        var allShopSpisok = globalSpisokService.getAllShopSpisok();
        model.addAttribute("spisokShop", allShopSpisok);
        return "start_page_shopList";
    }
}
