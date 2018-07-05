package com.codecool.bread.model;

import java.sql.Date;

public final class CustomerOrder extends POSObject{

    private int seatId;
    private int employeeId;
    private Date orderingTime;
    private int orderItemId;
    private int invoiceId;

    public CustomerOrder(int id, int seatId, int employeeId, Date orderingTime, int orderItemId, int invoiceId) {
        super(id);
        this.seatId = seatId;
        this.employeeId = employeeId;
        this.orderingTime = orderingTime;
        this.orderItemId = orderItemId;
        this.invoiceId = invoiceId;
    }

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
}
