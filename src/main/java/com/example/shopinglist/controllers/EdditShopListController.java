package com.example.shopinglist.controllers;

import com.example.shopinglist.services.ServiceGoods;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("edditSpisok")
public class EdditShopListController {
    private final ServiceGoods serviceGoods;

    @GetMapping("/{idSpisok}")
    public String edditSpisok(@PathVariable Long idSpisok, Model model) {
        var goodsList = serviceGoods.allGoodsFromCurrentShopList(idSpisok);
        model.addAttribute("idSpisok", idSpisok);
        model.addAttribute("allGoods", goodsList);
        return "eddit_shopList";
    }

    @PostMapping("/{idSpisok}/newGood")
    public String addNewGood(@PathVariable Long idSpisok, Model model,
                             @RequestParam String nameGood) {
        serviceGoods.createNewGood(nameGood, idSpisok);
        return "redirect:/edditSpisok/{idSpisok}";
    }

    @PostMapping("/{idSpisok}/deleteGood/{idGoods}")
    public String deleteGood(@PathVariable Long idGoods,
                             @PathVariable Long idSpisok,
                             Model model){
        var f = serviceGoods.deleteGood(idGoods);
        return "redirect:/edditSpisok/{idSpisok}";
    }
}
