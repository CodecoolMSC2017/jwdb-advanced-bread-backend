package com.codecool.bread.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal total;
    private Date date;

    public Invoice(int id, BigDecimal total, Date date) {
        this.id = id;
        this.total = total;
        this.date = date;
    }

    public Invoice(){
    }

    public int getId(){
        return id;
    }

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
