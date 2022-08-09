package com.example.shopinglist.controllers.profile;

import com.example.shopinglist.DTO.AuthUserDTO;
import com.example.shopinglist.DTO.Converter;
import com.example.shopinglist.auth_model.AuthUserModel;
import com.example.shopinglist.models.profile.UserProfile;
import com.example.shopinglist.services.AuthUserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class ProfileController {

    private final AuthUserService authUserService;

    public ProfileController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @GetMapping(value = "profile/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<AuthUserDTO> getProfile(@PathVariable Long userId) {
        AuthUserModel currentUser = authUserService.getUserFromContext();
        AuthUserDTO userDTO = Converter.convertAuthUserModelT0DTO(currentUser);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AuthUserDTO> entity = restTemplate.postForEntity("http://localhost:8090/profile/" + userId, userDTO, AuthUserDTO.class);
        return entity;
    }
}
