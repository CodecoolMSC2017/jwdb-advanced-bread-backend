package com.codecool.bread.model;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "customer_order")
public class CustomerOrder extends POSObject {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_id")
    private Seat seat;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Column(name = "ordering_time")
    private Date orderingTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_item_id")
    private OrderItem order;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_id")
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
