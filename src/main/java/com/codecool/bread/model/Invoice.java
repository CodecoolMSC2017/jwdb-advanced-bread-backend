package com.codecool.bread.model;

import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "invoice")
public class Invoice extends POSObject {

    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal total;

    private Date date = new Date(new java.util.Date().getTime());

    @Column(name = "paid", columnDefinition = "boolean default false")
    private Boolean isPaid = false;

    public Invoice(BigDecimal total) {
        this.total = total;
    }

    protected Invoice() {  /* as required by JPA */
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }
}
