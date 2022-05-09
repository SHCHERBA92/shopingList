package com.example.shopinglist.DTO;

import com.example.shopinglist.models.GlobalSpisokModel;
import com.example.shopinglist.models.GoodsModel;
import org.modelmapper.ModelMapper;

public class Converter {
    private final ModelMapper modelMapper;
    public Converter() {
        this.modelMapper = new ModelMapper();
    }

    GoodsDTO converterGoodsModelToGoodsDTO(GoodsModel model){
        return modelMapper.map(model, GoodsDTO.class);
    }

    GlobalSpisokDTO converterStatusModelToStatusDTO(GlobalSpisokModel model){
        return modelMapper.map(model, GlobalSpisokDTO.class);
    }
}
