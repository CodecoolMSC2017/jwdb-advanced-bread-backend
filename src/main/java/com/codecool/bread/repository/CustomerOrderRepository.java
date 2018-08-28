package com.codecool.bread.repository;

import com.codecool.bread.model.CustomerOrder;
import com.codecool.bread.model.dto.StatsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {

    Optional<CustomerOrder> findByIdAndSeatId(Integer id, Integer seatId);

    Set<CustomerOrder> findBySeatIdAndEnabledTrue(Integer seatId);

    List<CustomerOrder> findByInvoiceId(int invoiceId);

    @Query(value = "SELECT * FROM customer_order WHERE order_item_id = ?1 and invoice_id IS null", nativeQuery = true)
    CustomerOrder findByOrderItem(Integer orderItemId);

    @Query(value = "SELECT customer_order.order_item_id AS OrderItemId, order_item.quantity AS OrderItemQuantity FROM order_item\n" +
            "JOIN customer_order ON customer_order.order_item_id = order_item.id\n" +
            "JOIN employee ON employee.id = customer_order.employee_id\n" +
            "JOIN restaurant ON restaurant.id = employee.restaurant_id\n" +
            "WHERE customer_order.ordering_time BETWEEN ?1 AND ?2", nativeQuery = true)
    List<StatsDto> findOrderItemQuantityById(Date start, Date end);
}
