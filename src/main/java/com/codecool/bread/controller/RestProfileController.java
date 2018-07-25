package com.codecool.bread.controller;

import com.codecool.bread.model.dto.ProfileDto;
import com.codecool.bread.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/profile")
public class RestProfileController extends AbstractController{

    @Autowired
    private ProfileService profileService;

    @GetMapping("")
    public ProfileDto getProfile(Principal principal) {
        return profileService.getProfileFromDb(getUserId(principal));
    }
}
