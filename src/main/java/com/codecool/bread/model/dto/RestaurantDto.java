package com.codecool.bread.model.dto;

import java.util.Set;

public class RestaurantDto {

    private int id;

    private Set<TableDto> tableDtoSet;

    public RestaurantDto(int id, Set<TableDto> tableDtoSet) {
        this.id = id;
        this.tableDtoSet = tableDtoSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<TableDto> getTableDtoSet() {
        return tableDtoSet;
    }

    public void setTableDtoSet(Set<TableDto> tableDtoSet) {
        this.tableDtoSet = tableDtoSet;
    }
}
