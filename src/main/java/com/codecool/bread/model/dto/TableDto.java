package com.codecool.bread.model.dto;

import com.codecool.bread.model.Seat;

import java.util.Set;

public class TableDto {

    private int id;
    private String name;
    private Set<SeatDto> seatDtoSet;
    private String assignedTo;

    public TableDto(int id, String name, Set<SeatDto> seatDtoSet) {
        this.id = id;
        this.name = name;
        this.seatDtoSet = seatDtoSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SeatDto> getSeatDtoSet() {
        return seatDtoSet;
    }

    public void setSeatDtoSet(Set<SeatDto> seatDtoSet) {
        this.seatDtoSet = seatDtoSet;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
}
