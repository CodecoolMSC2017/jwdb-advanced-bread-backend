package com.codecool.bread.model.dto;

import com.codecool.bread.model.CustomerOrder;

import java.util.List;

public class SeatDto {

    private Integer id;
    private List<CustomerOrder> customerOrderList;

    public SeatDto() {
    }

    public SeatDto(Integer id, List<CustomerOrder> customerOrderList) {
        this.id = id;
        this.customerOrderList = customerOrderList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CustomerOrder> getCustomerOrderList() {
        return customerOrderList;
    }

    public void setCustomerOrderList(List<CustomerOrder> customerOrderList) {
        this.customerOrderList = customerOrderList;
    }
}
