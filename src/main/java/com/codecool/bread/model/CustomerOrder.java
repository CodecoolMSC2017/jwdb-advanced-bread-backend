package com.codecool.bread.model;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "customer_order")
public class CustomerOrder extends POSObject{

    @Column(name = "seat_id")
    private int seatId;
    @Column(name = "employee_id")
    private int employeeId;
    @Column(name = "ordering_time")
    private Date orderingTime;
    @Column(name = "order_item_id")
    private int orderItemId;
    @Column(name = "invoice_id")
    private int invoiceId;

    public int getSeatId() {
        return seatId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public Date getOrderingTime() {
        return orderingTime;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setOrderingTime(Date orderingTime) {
        this.orderingTime = orderingTime;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
}
