package com.codecool.bread.service;

import com.codecool.bread.model.dto.StatsDto;

public interface StatisticsService {

    StatsDto getAllRestaurantIncomeAvgFromDb(int ownerId, int year, int month);

    StatsDto getRestaurantIncomeAvgFromDb(int restaurantId, int year, int month);
}
