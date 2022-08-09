package com.example.shopinglist.DTO;

import com.example.shopinglist.DTO.profil.UserProfileDTO;
import com.example.shopinglist.auth_model.AuthUserModel;
import com.example.shopinglist.models.GlobalSpisokModel;
import com.example.shopinglist.models.GoodsModel;
import com.example.shopinglist.models.profile.UserProfile;
import org.modelmapper.ModelMapper;

public class Converter {
    private static ModelMapper modelMapper = new ModelMapper();

//    public Converter() {
//        this.modelMapper = new ModelMapper();
//    }

    public static GoodsDTO converterGoodsModelToGoodsDTO(GoodsModel model) {
        return modelMapper.map(model, GoodsDTO.class);
    }


    public static GlobalSpisokDTO converterStatusModelToStatusDTO(GlobalSpisokModel model) {
        return modelMapper.map(model, GlobalSpisokDTO.class);
    }

    public static UserProfileDTO convertUserProfileToDTO(UserProfile userProfile) {
        return modelMapper.map(userProfile, UserProfileDTO.class);
    }

    public static UserProfile convertUserProfileDTOtoUserProfile(UserProfileDTO userProfileDTO) {
        return modelMapper.map(userProfileDTO, UserProfile.class);
    }

    public static AuthUserDTO convertAuthUserModelT0DTO(AuthUserModel model){
        return modelMapper.map(model, AuthUserDTO.class);
    }
}
