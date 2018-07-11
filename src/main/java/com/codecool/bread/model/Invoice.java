package com.codecool.bread.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "invoice")
public class Invoice  extends POSObject {

    @Column(columnDefinition = "DECIMAL(9,2)")
    private BigDecimal total;
    private Date date;

    public BigDecimal getTotal() {
        return total;
    }

    @OneToMany(mappedBy = "invoice")
    private Set<CustomerOrder> orders = new HashSet<>();

    public Date getDate() {
        return date;
    }


    public Set<CustomerOrder> getOrders() {
        return orders;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setOrders(Set<CustomerOrder> orders) {
        this.orders = orders;
    }
}
