package com.codecool.bread.model.dto;

import com.codecool.bread.model.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InvoiceDto {

    private int id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime created;
    private int employeeId;
    private Address restaurantAddress;
    private BigDecimal totalPrice;
    private List<InvoiceItemDto> invoiceItemDtos;

    public InvoiceDto() {
    }

    public InvoiceDto(int id, LocalDateTime created, int employeeId, Address restaurantAddress, BigDecimal totalPrice, List<InvoiceItemDto> invoiceItemDtos) {
        this.id = id;
        this.created = created;
        this.employeeId = employeeId;
        this.restaurantAddress = restaurantAddress;
        this.totalPrice = totalPrice;
        this.invoiceItemDtos = invoiceItemDtos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Address getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(Address restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<InvoiceItemDto> getInvoiceItemDtos() {
        return invoiceItemDtos;
    }

    public void setInvoiceItemDtos(List<InvoiceItemDto> invoiceItemDtos) {
        this.invoiceItemDtos = invoiceItemDtos;
    }
}
