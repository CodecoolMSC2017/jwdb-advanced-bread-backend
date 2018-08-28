package com.codecool.bread.model.dto;

public class StatsDto {

    private int ownerId;
    private int restaurantId;
    private int incomeAvg;
    private int allIncomeAvg;
    private int incomeSum;
    private int allIncomeSum;
    private int orderItemId;
    private int orderItemQuantity;

    public StatsDto(){}

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public StatsDto(int allIncomeAvg) {
        this.allIncomeAvg = allIncomeAvg;
    }

    public int getRestaurantId() {
        return restaurantId;
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

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getIncomeSum() {
        return incomeSum;
    }

    public void setIncomeSum(int incomeSum) {
        this.incomeSum = incomeSum;
    }

    public int getAllIncomeSum() {
        return allIncomeSum;
    }

    public void setAllIncomeSum(int allIncomeSum) {
        this.allIncomeSum = allIncomeSum;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderItemQuantity() {
        return orderItemQuantity;
    }

    public void setOrderItemQuantity(int orderItemQuantity) {
        this.orderItemQuantity = orderItemQuantity;
    }
}
