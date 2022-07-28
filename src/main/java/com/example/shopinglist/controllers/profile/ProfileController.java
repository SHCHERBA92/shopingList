package com.example.shopinglist.controllers.profile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {

    @GetMapping("profile/{userId}")
    String getProfile(@PathVariable Long userId){
        return "start_of_profile";
    }
}
