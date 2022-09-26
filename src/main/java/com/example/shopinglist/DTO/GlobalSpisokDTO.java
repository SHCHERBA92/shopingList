package com.example.shopinglist.DTO;

import com.example.shopinglist.models.GoodsModel;

import java.time.LocalDate;
import java.util.List;

public class GlobalSpisokDTO {

    private LocalDate createAt = LocalDate.now();

    private LocalDate dateTo;

    private String storeName;

    private String nameOfShopList;

    List<GoodsModel> goodsModels;
}
