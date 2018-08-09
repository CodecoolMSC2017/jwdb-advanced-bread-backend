package com.codecool.bread.model.dto;

import com.codecool.bread.model.Employee;

import java.time.LocalDateTime;
import java.util.List;

public class TableDto {

    private int id;
    private String name;
    private Employee employee;
    private LocalDateTime arrivalTime;
    private List<SeatDto> seatDtoSet;

    public TableDto() {
    }

    public TableDto(int id, String name, List<SeatDto> seatDtoSet) {
        this.id = id;
        this.name = name;
        this.seatDtoSet = seatDtoSet;
    }

    public TableDto(int id, String name, LocalDateTime arrivalTime, List<SeatDto> seatDtoSet) {
        this.id = id;
        this.name = name;
        this.arrivalTime = arrivalTime;
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

    public List<SeatDto> getSeatDtoSet() {
        return seatDtoSet;
    }

    public void setSeatDtoSet(List<SeatDto> seatDtoSet) {
        this.seatDtoSet = seatDtoSet;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
