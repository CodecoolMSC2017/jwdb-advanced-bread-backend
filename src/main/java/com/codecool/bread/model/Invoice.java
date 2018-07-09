package com.codecool.bread.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "invoice")
public class Invoice  extends POSObject {

    private BigDecimal total;
    private Date date;

    public BigDecimal getTotal() {
        return total;
    }

    public Date getDate() {
        return date;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
