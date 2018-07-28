package com.codecool.bread.model.dto;

import com.codecool.bread.model.Seat;

import java.util.Set;

public class TableDto {

    private int id;
    private Set<SeatDto> seatDtoSet;

    public TableDto(int id, Set<SeatDto> seatDtoSet) {
        this.id = id;
        this.seatDtoSet = seatDtoSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<SeatDto> getSeatDtoSet() {
        return seatDtoSet;
    }

    public void setSeatDtoSet(Set<SeatDto> seatDtoSet) {
        this.seatDtoSet = seatDtoSet;
    }
}
