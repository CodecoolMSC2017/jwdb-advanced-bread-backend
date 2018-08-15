package com.codecool.bread.repository;

import com.codecool.bread.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    Optional<Invoice> findById(Integer invoiceId);

    @Query(value = "SELECT avg(subselect.total) FROM (SELECT avg(distinct invoice.total) as total FROM customer_order\n" +
            "JOIN invoice ON invoice.id = customer_order.invoice_id\n" +
            "JOIN employee ON employee.id = customer_order.employee_id\n" +
            "JOIN restaurant ON restaurant.id = employee.restaurant_id\n" +
            "WHERE restaurant.owner_id = ?1\n" +
            "AND EXTRACT(YEAR FROM invoice.date) = ?2\n" +
            "AND EXTRACT(MONTH FROM invoice.date) = ?3\n" +
            "AND invoice.date IS NOT NULL\n" +
            "GROUP BY invoice.id) AS subselect", nativeQuery = true)
    Integer findInvoiceAvgByOwnerId(Integer ownerId, Integer year, Integer month);

    @Query(value = "SELECT avg(subselect.total) FROM (SELECT avg(distinct invoice.total) as total FROM customer_order\n" +
            "JOIN invoice ON invoice.id = customer_order.invoice_id\n" +
            "JOIN employee ON employee.id = customer_order.employee_id\n" +
            "JOIN restaurant ON restaurant.id = employee.restaurant_id\n" +
            "WHERE restaurant.id = ?1\n" +
            "AND EXTRACT(YEAR FROM invoice.date) = ?2\n" +
            "AND EXTRACT(MONTH FROM invoice.date) = ?3\n" +
            "AND invoice.date IS NOT NULL\n" +
            "GROUP BY invoice.id) AS subselect", nativeQuery = true)
    Integer findInvoiceAvgByRestaurantId(Integer restaurantId, Integer year, Integer month);

    @Query(value = "SELECT sum(subselect.total) FROM (SELECT sum(distinct invoice.total) as total FROM customer_order\n" +
            "JOIN invoice ON invoice.id = customer_order.invoice_id\n" +
            "JOIN employee ON employee.id = customer_order.employee_id\n" +
            "JOIN restaurant ON restaurant.id = employee.restaurant_id\n" +
            "WHERE restaurant.owner_id = ?1\n" +
            "AND EXTRACT(YEAR FROM invoice.date) = ?2\n" +
            "AND EXTRACT(MONTH FROM invoice.date) = ?3\n" +
            "AND invoice.date IS NOT NULL\n" +
            "GROUP BY invoice.id) AS subselect", nativeQuery = true)
    Integer findInvoiceSumByOwnerId(Integer ownerId, Integer year, Integer month);

    @Query(value = "SELECT sum(subselect.total) FROM (SELECT sum(distinct invoice.total) as total FROM customer_order\n" +
            "JOIN invoice ON invoice.id = customer_order.invoice_id\n" +
            "JOIN employee ON employee.id = customer_order.employee_id\n" +
            "JOIN restaurant ON restaurant.id = employee.restaurant_id\n" +
            "WHERE restaurant.id = ?1\n" +
            "AND EXTRACT(YEAR FROM invoice.date) = ?2\n" +
            "AND EXTRACT(MONTH FROM invoice.date) = ?3\n" +
            "AND invoice.date IS NOT NULL\n" +
            "GROUP BY invoice.id) AS subselect", nativeQuery = true)
    Integer findInvoiceSumByRestaurantId(Integer restaurantId, Integer year, Integer month);
}
