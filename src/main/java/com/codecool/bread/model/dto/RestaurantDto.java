package com.codecool.bread.model.dto;

import java.util.List;

public class RestaurantDto {

    private int id;

    private List<TableDto> tableDtoSet;

    public RestaurantDto(int id, List<TableDto> tableDtoSet) {
        this.id = id;
        this.tableDtoSet = tableDtoSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TableDto> getTableDtoSet() {
        return tableDtoSet;
    }

    public void setTableDtoSet(List<TableDto> tableDtoSet) {
        this.tableDtoSet = tableDtoSet;
    }
}
