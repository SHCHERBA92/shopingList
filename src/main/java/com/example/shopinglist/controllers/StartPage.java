package com.example.shopinglist.controllers;

import com.example.shopinglist.models.GlobalSpisokModel;
import com.example.shopinglist.services.GlobalSpisokService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class StartPage {

    private final GlobalSpisokService globalSpisokService;

    @GetMapping("startPage")
    public String startPage(Model model) {
        var allShopSpisok = globalSpisokService.getAllShopSpisok();
        List<String> tempGoodsForSpisok = new ArrayList<>();
        model.addAttribute("spisokShop", allShopSpisok);
        model.addAttribute("tempGoodsForSpisok", tempGoodsForSpisok);

        return "start_page_shopList";
    }

    @PostMapping("createNewList")
    public String createNewList(@RequestParam String listName,
                                @RequestParam String storeName,
                                @RequestParam("dateToShop") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateToShop
    ) {
        LocalDate localDate = LocalDate.ofInstant(dateToShop.toInstant(), ZoneId.systemDefault());
        globalSpisokService.addNewSpisok(listName, storeName, localDate);
        return "redirect:/startPage";
    }

}
