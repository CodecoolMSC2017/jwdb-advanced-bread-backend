package com.codecool.bread.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "customer_order")
public class CustomerOrder extends POSObject {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_id")
    @JsonBackReference
    private Seat seat;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee employee;
    @Column(name = "ordering_time")
    private Date orderingTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_item_id")
    @JsonBackReference
    private OrderItem order;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_id")
    @JsonBackReference
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

    public Date getOrderingTime() {
        return orderingTime;
    }

    public void setOrderingTime(Date orderingTime) {
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
}
