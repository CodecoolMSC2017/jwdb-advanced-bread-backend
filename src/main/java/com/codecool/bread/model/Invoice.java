package com.codecool.bread.model;

import java.math.BigDecimal;
import java.sql.Date;

public final class Invoice extends POSObject{
    private BigDecimal total;
    private Date date;

    public Invoice(int id, BigDecimal total, Date date) {
        super(id);
        this.total = total;
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Date getDate() {
        return date;
    }
}
