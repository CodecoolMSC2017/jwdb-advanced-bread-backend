package com.codecool.bread.model.dto;

public class StatsDto {

    private int restaurantId;
    private int incomeAvg;
    private int allIncomeAvg;

    public StatsDto(int allIncomeAvg) {
        this.allIncomeAvg = allIncomeAvg;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getIncomeAvg() {
        return incomeAvg;
    }

    public void setIncomeAvg(int incomeAvg) {
        this.incomeAvg = incomeAvg;
    }

    public int getAllIncomeAvg() {
        return allIncomeAvg;
    }

    public void setAllIncomeAvg(int allIncomeAvg) {
        this.allIncomeAvg = allIncomeAvg;
    }
}
