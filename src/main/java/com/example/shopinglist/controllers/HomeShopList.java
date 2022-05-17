package com.example.shopinglist.controllers;

import com.example.shopinglist.exceptions.ExceptionNotElements;
import com.example.shopinglist.services.GlobalSpisokService;
import com.example.shopinglist.services.ServiceGoods;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/shopList")
public class HomeShopList {

    private final ServiceGoods serviceGoods;
    private final GlobalSpisokService globalSpisokService;

    @GetMapping("/{id}")
    public String getHomePage(@PathVariable Long id, Model model){
        var goodsList =  serviceGoods.allGoodsFromCurrentShopList(id);
        try {
            checkGoodsList(goodsList);
        }catch (ExceptionNotElements elements){
            model.addAttribute("error", elements.getMessage());
        }
        model.addAttribute("goodsCurrentShopList", goodsList);
        return "shop-list";
    }

    private void checkGoodsList(List goodsList) throws ExceptionNotElements{
        if (CollectionUtils.isEmpty(goodsList))
            throw new ExceptionNotElements("Такого списка нет или он пуст");
    }

}
