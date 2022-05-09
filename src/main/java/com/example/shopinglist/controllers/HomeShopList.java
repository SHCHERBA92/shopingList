package com.example.shopinglist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeShopList {

    @GetMapping
    public String getHomePage(){
        return "home-shop-list";
    }



}
