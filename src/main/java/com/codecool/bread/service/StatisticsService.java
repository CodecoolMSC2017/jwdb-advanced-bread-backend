package com.codecool.bread.service;

import com.codecool.bread.model.dto.StatsDto;

import java.util.Date;
import java.util.List;

public interface StatisticsService {

    List<StatsDto> getAllRestaurantIncomeAvgFromDb(int ownerId, Date start, Date end);

    List<StatsDto> getAllRestaurantIncomeSumFromDb(int ownerId, Date start, Date end);

    List<StatsDto> getNumOfGuestsFromDb(int restaurantId, Date start, Date end);

    List<StatsDto> getAllOrderQuantityByFromDb(int restaurantId, Date start, Date end);

    List<StatsDto> getOrderQuantityByItemIdFromDb(int restaurantId,int itemId, Date start, Date end);

    }
