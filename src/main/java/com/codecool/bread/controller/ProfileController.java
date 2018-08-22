package com.codecool.bread.controller;

import com.codecool.bread.model.dto.ProfileDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/profile")
public class ProfileController extends AbstractController{

    @GetMapping("")
    public ProfileDto getProfile(Principal principal) {
        return profileService.get(getUserId(principal));
    }
}
