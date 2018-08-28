package com.codecool.bread.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoice")
public class Invoice extends POSObject {

    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal total;

    @JsonFormat(pattern = "yyyy-mm-dd-KK:mm:ss")
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "paid", columnDefinition = "boolean default false")
    private Boolean isPaid = false;

    public Invoice(BigDecimal total) {
        this.total = total;
    }

    protected Invoice() {  // as required by JPA
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }
}
