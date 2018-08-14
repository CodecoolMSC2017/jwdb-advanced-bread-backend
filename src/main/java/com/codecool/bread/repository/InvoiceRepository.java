package com.codecool.bread.repository;

import com.codecool.bread.model.Invoice;
import com.codecool.bread.model.dto.StatsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    Optional<Invoice> findById(Integer invoiceId);

    @Query(value = "SELECT AVG(invoice.total) AS income_avg FROM customer_order\n" +
            "JOIN invoice ON invoice.id = customer_order.invoice_id\n" +
            "JOIN employee ON employee.id = customer_order.employee_id\n" +
            "JOIN restaurant ON restaurant.id = employee.restaurant_id\n" +
            "WHERE restaurant.owner_id = ?1\n" +
            "AND EXTRACT(YEAR FROM invoice.date) = ?2\n" +
            "AND EXTRACT(MONTH FROM invoice.date) = ?3", nativeQuery = true)
    Integer findInvoiceSumsByOwnerId(Integer ownerId, Integer year, Integer month);
}
