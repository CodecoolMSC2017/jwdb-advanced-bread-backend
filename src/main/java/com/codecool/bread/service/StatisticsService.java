package com.codecool.bread.service;

import com.codecool.bread.model.dto.StatsDto;

import java.util.Date;

public interface StatisticsService {

    StatsDto getAllRestaurantIncomeAvgFromDb(int ownerId, Date start, Date end);

    StatsDto getRestaurantIncomeAvgFromDb(int restaurantId, Date start, Date end);

    StatsDto getAllRestaurantIncomeSumFromDb(int ownerId, Date start, Date end);

    StatsDto getRestaurantIncomeSumFromDb(int restaurantId, Date start, Date end);

    StatsDto getOrderQuantityByItemIdFromDb(int orderItemId, Date start, Date end);
}
