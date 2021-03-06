package com.codecool.bread.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_order")
public class CustomerOrder extends POSObject implements Comparable<CustomerOrder> {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_id")
    @JsonIgnore
    private Seat seat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;

    @Column(name = "ordering_time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime orderingTime = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_item_id")
    //@JsonIgnore
    private OrderItem order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_id")
    @JsonIgnore
    private Invoice invoice;

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDateTime getOrderingTime() {
        return orderingTime;
    }

    public void setOrderingTime(LocalDateTime orderingTime) {
        this.orderingTime = orderingTime;
    }

    public OrderItem getOrderItem() {
        return order;
    }

    public void setOrderItem(OrderItem order) {
        this.order = order;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public int compareTo(CustomerOrder o) {
        return (this.getOrderItem().getItem().getName().compareTo(o.getOrderItem().getItem().getName()));
    }
}
