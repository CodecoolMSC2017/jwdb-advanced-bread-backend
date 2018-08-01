package com.codecool.bread.model.dto;

import com.codecool.bread.model.CustomerOrder;

import java.util.Set;

public class SeatDto {

    private int id;
    Set<CustomerOrder> customerOrderSet;

    public SeatDto() {
    }

    public SeatDto(int id, Set<CustomerOrder> customerOrderSet) {
        this.id = id;
        this.customerOrderSet = customerOrderSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<CustomerOrder> getCustomerOrderSet() {
        return customerOrderSet;
    }

    public void setCustomerOrderSet(Set<CustomerOrder> customerOrderSet) {
        this.customerOrderSet = customerOrderSet;
    }
}
