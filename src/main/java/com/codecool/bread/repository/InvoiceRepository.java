package com.codecool.bread.repository;

import com.codecool.bread.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    Optional<Invoice> findById(Integer invoiceId);

    @Query(value = "SELECT avg(subselect.total) FROM (SELECT avg(distinct invoice.total) as total FROM customer_order\n" +
            "JOIN invoice ON invoice.id = customer_order.invoice_id\n" +
            "JOIN employee ON employee.id = customer_order.employee_id\n" +
            "JOIN restaurant ON restaurant.id = employee.restaurant_id\n" +
            "WHERE restaurant.owner_id = ?1\n" +
            "AND invoice.date BETWEEN ?2 AND ?3\n" +
            "AND invoice.date IS NOT NULL\n" +
            "GROUP BY invoice.id) AS subselect", nativeQuery = true)
    Integer findInvoiceAvgByOwnerId(Integer ownerId, Date start, Date end);

    @Query(value = "SELECT avg(subselect.total) FROM (SELECT avg(distinct invoice.total) as total FROM customer_order\n" +
            "JOIN invoice ON invoice.id = customer_order.invoice_id\n" +
            "JOIN employee ON employee.id = customer_order.employee_id\n" +
            "JOIN restaurant ON restaurant.id = employee.restaurant_id\n" +
            "WHERE restaurant.id = ?1\n" +
            "AND invoice.date BETWEEN ?2 AND ?3\n" +
            "AND invoice.date IS NOT NULL\n" +
            "GROUP BY invoice.id) AS subselect", nativeQuery = true)
    Integer findInvoiceAvgByRestaurantId(Integer restaurantId, Date start, Date end);

    @Query(value = "SELECT sum(subselect.total) FROM (SELECT sum(distinct invoice.total) as total FROM customer_order\n" +
            "JOIN invoice ON invoice.id = customer_order.invoice_id\n" +
            "JOIN employee ON employee.id = customer_order.employee_id\n" +
            "JOIN restaurant ON restaurant.id = employee.restaurant_id\n" +
            "WHERE restaurant.owner_id = ?1\n" +
            "AND invoice.date BETWEEN ?2 AND ?3\n" +
            "AND invoice.date IS NOT NULL\n" +
            "GROUP BY invoice.id) AS subselect", nativeQuery = true)
    Integer findInvoiceSumByOwnerId(Integer ownerId, Date start, Date end);

    @Query(value = "SELECT sum(subselect.total) FROM (SELECT sum(distinct invoice.total) as total FROM customer_order\n" +
            "JOIN invoice ON invoice.id = customer_order.invoice_id\n" +
            "JOIN employee ON employee.id = customer_order.employee_id\n" +
            "JOIN restaurant ON restaurant.id = employee.restaurant_id\n" +
            "WHERE restaurant.id = ?1\n" +
            "AND invoice.date BETWEEN ?2 AND ?3\n" +
            "AND invoice.date IS NOT NULL\n" +
            "GROUP BY invoice.id) AS subselect", nativeQuery = true)
    Integer findInvoiceSumByRestaurantId(Integer restaurantId, Date start, Date end);
}
