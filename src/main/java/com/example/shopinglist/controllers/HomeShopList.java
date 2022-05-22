package com.example.shopinglist.controllers;

import com.example.shopinglist.exceptions.ExceptionNotElements;
import com.example.shopinglist.models.RoleOfStatus;
import com.example.shopinglist.services.GlobalSpisokService;
import com.example.shopinglist.services.ServiceGoods;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/shopList")
public class HomeShopList {

    private final ServiceGoods serviceGoods;
    private final GlobalSpisokService globalSpisokService;

    //TODO: сделать несколько моделей для списков с разным статусом
    @GetMapping("/{id}")
    public String getHomePage(@PathVariable Long id, Model model){
        var goodsList =  serviceGoods.allGoodsFromCurrentShopList(id);
        var goodsListREADY_BUY = serviceGoods.allGoodsFromCurrentShopList(id, RoleOfStatus.READY_BUY);
        var goodsListBUY = serviceGoods.allGoodsFromCurrentShopList(id, RoleOfStatus.BUY);
        var goodsListCANCEL = serviceGoods.allGoodsFromCurrentShopList(id, RoleOfStatus.CANCEL);

        String error = null;
        try {
            checkGoodsList(goodsList);
        }catch (ExceptionNotElements elements){
            error = elements.getMessage();
        }
        model.addAttribute("error", error);
        model.addAttribute("goodsCurrentShopList", goodsList);
        model.addAttribute("goodsREADY_BYU", goodsListREADY_BUY);
        model.addAttribute("goodsBYU", goodsListBUY);
        model.addAttribute("goodsCANCEL", goodsListCANCEL);

        return "shop-list";
    }

    private void checkGoodsList(List goodsList) throws ExceptionNotElements{
        if (CollectionUtils.isEmpty(goodsList))
            throw new ExceptionNotElements("Такого списка нет или он пуст");
    }



}
