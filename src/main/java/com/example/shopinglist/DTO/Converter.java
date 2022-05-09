package com.example.shopinglist.DTO;

import com.example.shopinglist.models.GoodsModel;
import com.example.shopinglist.models.StatusModel;
import org.modelmapper.ModelMapper;

public class Converter {
    private final ModelMapper modelMapper;
    public Converter() {
        this.modelMapper = new ModelMapper();
    }

    GoodsDTO converterGoodsModelToGoodsDTO(GoodsModel model){
        return modelMapper.map(model, GoodsDTO.class);
    }

    StatusDTO converterStatusModelToStatusDTO(StatusModel model){
        return modelMapper.map(model, StatusDTO.class);
    }
}
