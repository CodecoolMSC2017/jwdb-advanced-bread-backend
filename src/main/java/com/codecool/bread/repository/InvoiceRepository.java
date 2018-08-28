package com.codecool.bread.repository;

import com.codecool.bread.model.Invoice;
import com.codecool.bread.model.dto.StatsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    Optional<Invoice> findById(Integer invoiceId);

    @Query(value = "SELECT subselect.restaurantId, avg(subselect.total) AS incomeAvg FROM (SELECT restaurant.id as restaurantId, avg(distinct invoice.total) as total FROM customer_order\n" +
            "JOIN invoice ON invoice.id = customer_order.invoice_id\n" +
            "JOIN employee ON employee.id = customer_order.employee_id\n" +
            "JOIN restaurant ON restaurant.id = employee.restaurant_id\n" +
            "WHERE restaurant.owner_id = ?1\n" +
            "AND invoice.date BETWEEN ?2 AND ?3\n" +
            "AND invoice.date IS NOT NULL\n" +
            "GROUP BY restaurant.id, invoice.id) AS subselect\n" +
            "GROUP BY (subselect.restaurantId)", nativeQuery = true)
    List<StatsDto> findInvoiceAvgByOwnerId(Integer ownerId, Date start, Date end);

    @Query(value = "SELECT subselect.restaurantId, sum(subselect.total) AS incomeSum FROM (SELECT restaurant.id as restaurantId, sum(distinct invoice.total) as total FROM customer_order\n" +
            "JOIN invoice ON invoice.id = customer_order.invoice_id\n" +
            "JOIN employee ON employee.id = customer_order.employee_id\n" +
            "JOIN restaurant ON restaurant.id = employee.restaurant_id\n" +
            "WHERE restaurant.owner_id = ?1\n" +
            "AND invoice.date BETWEEN ?2 AND ?3\n" +
            "AND invoice.date IS NOT NULL\n" +
            "GROUP BY restaurant.id, invoice.id) AS subselect\n" +
            "GROUP BY (subselect.restaurantId)", nativeQuery = true)
    List<StatsDto> findInvoiceSumByOwnerId(Integer ownerId, Date start, Date end);
}
