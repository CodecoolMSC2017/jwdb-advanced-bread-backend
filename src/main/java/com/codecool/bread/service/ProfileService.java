package com.codecool.bread.service;

import com.codecool.bread.model.dto.ProfileDto;

public interface ProfileService {
    ProfileDto get(int userId);
}
