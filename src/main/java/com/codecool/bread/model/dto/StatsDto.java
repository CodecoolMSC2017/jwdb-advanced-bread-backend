package com.codecool.bread.model.dto;

import java.util.Date;

public interface StatsDto {

    Integer getOwnerId();

    Integer getRestaurantId();

    Integer getIncomeAvg();

    Integer getIncomeSum();

    Integer getItemId();

    Integer getItemQuantity();

    String getItemName();

    Integer getNumOfGuests();

    Date getDate();
}
