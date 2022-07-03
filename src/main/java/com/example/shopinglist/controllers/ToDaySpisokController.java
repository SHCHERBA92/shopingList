package com.example.shopinglist.controllers;

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
    private final ServiceGoods serviceGoods;


    @GetMapping
    public String getToDaySpisok(Model model){
        var allShopSpisok = globalSpisokService.getAllToDaySpisok();
        model.addAttribute("spisokShop", allShopSpisok);
        return "start_page_shopList";
    }
}
