package com.codecool.bread.model.dto;

import com.codecool.bread.model.CustomerOrder;

import java.util.List;

public class SeatDto {

    private int id;
    private List<CustomerOrder> customerOrderList;

    public SeatDto() {
    }

    public SeatDto(int id, List<CustomerOrder> customerOrderList) {
        this.id = id;
        this.customerOrderList = customerOrderList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CustomerOrder> getCustomerOrderSet() {
        return customerOrderList;
    }

    public void setCustomerOrderSet(List<CustomerOrder> customerOrderSet) {
        this.customerOrderList = customerOrderSet;
    }
}
