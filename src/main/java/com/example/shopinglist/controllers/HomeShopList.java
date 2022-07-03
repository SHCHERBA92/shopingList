package com.example.shopinglist.controllers;

import com.example.shopinglist.exceptions.ExceptionNotElements;
import com.example.shopinglist.models.GoodsModel;
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

    @GetMapping("/{id}")
    public String getHomePage(@PathVariable Long id, Model model) {
        var goodsList = serviceGoods.allGoodsFromCurrentShopList(id);
        var goodsListREADY_BUY = serviceGoods.allGoodsFromCurrentShopList(id, RoleOfStatus.READY_BUY);
        var goodsListBUY = serviceGoods.allGoodsFromCurrentShopList(id, RoleOfStatus.BUY);
        var goodsListCANCEL = serviceGoods.allGoodsFromCurrentShopList(id, RoleOfStatus.CANCEL);
        var nameShoppingList = globalSpisokService.getCurrentSpisok(id).getNameOfShopList();

        String error = null;
        try {
            checkGoodsList(goodsList);
        } catch (ExceptionNotElements elements) {
            error = elements.getMessage();
        }
        model.addAttribute("error", error);
        model.addAttribute("goodsCurrentShopList", goodsList);
        model.addAttribute("goodsREADY_BYU", goodsListREADY_BUY);
        model.addAttribute("goodsBYU", goodsListBUY);
        model.addAttribute("goodsCANCEL", goodsListCANCEL);
        model.addAttribute("idSpisok", id);
        model.addAttribute("nameShoppingList", nameShoppingList);

        return "shop-list";
    }

    @PostMapping("/{id}/buyGood/{idGood}")
    public String buyCurrentGood(@PathVariable("id") Long id,
                                 @PathVariable("idGood") Long idGood,
                                 Model model){
        var goodToByu = serviceGoods.getGoodById(idGood);
        exchangeGoodRoleOfStatus(goodToByu, RoleOfStatus.BUY);
        return "redirect:/shopList/{id}";
    }

    @PostMapping("/{id}/cancelGood/{idGood}")
    public String cancelCurrentGood(@PathVariable("id") Long id,
                                 @PathVariable("idGood") Long idGood,
                                 Model model){
        var goodToByu = serviceGoods.getGoodById(idGood);
        exchangeGoodRoleOfStatus(goodToByu, RoleOfStatus.READY_BUY);
        return "redirect:/shopList/{id}";
    }

    private void checkGoodsList(List goodsList) throws ExceptionNotElements {
        if (CollectionUtils.isEmpty(goodsList))
            throw new ExceptionNotElements("Такого списка нет или он пуст");
    }

    private void exchangeGoodRoleOfStatus(GoodsModel model, RoleOfStatus role) {
        model.setRoleOfStatus(role);
        serviceGoods.createNewGood(model);
    }


}
