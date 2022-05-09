package com.example.shopinglist.DTO;

import com.example.shopinglist.models.GoodsModel;
import com.example.shopinglist.models.RoleOfStatus;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

public class GlobalSpisokDTO {

    private LocalDate createAt = LocalDate.now();

    private LocalDate dateTo;

    private String storeName;

    private String nameOfShopList;

    List<GoodsModel> goodsModels;
}
