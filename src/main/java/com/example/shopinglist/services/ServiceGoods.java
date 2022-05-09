package com.example.shopinglist.services;

import com.example.shopinglist.exceptions.ExceptionNotElements;
import com.example.shopinglist.models.GoodsModel;
import com.example.shopinglist.models.RoleOfStatus;
import com.example.shopinglist.repository.GoodsRepository;
import com.example.shopinglist.repository.GlobalSpisokRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceGoods {
    private final GoodsRepository goodsRepository;
    private final GlobalSpisokRepository statusRepository;

    private final String NOT_FIND_ELEMENT = "Элемента нет в базе данных";

    public ServiceGoods(GoodsRepository goodsRepository, GlobalSpisokRepository statusRepository) {
        this.goodsRepository = goodsRepository;
        this.statusRepository = statusRepository;
    }

    public GoodsModel getGoodsByName(String name){
        return goodsRepository.findByName(name).orElseThrow(()-> new ExceptionNotElements(NOT_FIND_ELEMENT));
    }

    public List<GoodsModel> allGoods(){
        return goodsRepository.findAll();
    }

    public List<GoodsModel> allGoodsFromRole(RoleOfStatus role){
        return goodsRepository.findByRoleOfStatus(role);
    }
}
