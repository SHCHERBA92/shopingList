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

    @GetMapping("/{id}")
    public String edditSpisok(@PathVariable Long id, Model model) {
        var goodsList = serviceGoods.allGoodsFromCurrentShopList(id);
        model.addAttribute("id", id);
        model.addAttribute("allGoods", goodsList);
        return "eddit_shopList";
    }

    @PostMapping("/{id}/newGood")
    public String addNewGood(@PathVariable Long id, Model model,
                             @RequestParam String nameGood) {
        serviceGoods.createNewGood(nameGood, id);
        return "redirect:/edditSpisok/{id}";
    }
}
