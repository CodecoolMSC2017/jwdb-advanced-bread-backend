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

    @Query(value = "SELECT restaurant.id, item.id AS itemId, item.name AS itemName, SUM(order_item.quantity) AS itemQuantity\n" +
            "FROM order_item\n" +
            "JOIN customer_order ON customer_order.order_item_id = order_item.id\n" +
            "JOIN item ON item.id = order_item.item_id\n" +
            "JOIN employee ON employee.id = customer_order.employee_id\n" +
            "JOIN restaurant ON restaurant.id = employee.restaurant_id\n" +
            "JOIN invoice ON invoice.id = customer_order.invoice_id\n" +
            "WHERE restaurant.id = ?1\n" +
            "AND invoice.paid IS TRUE\n" +
            "AND customer_order.ordering_time BETWEEN ?2 AND ?3\n" +
            "GROUP BY restaurant.id, item.id", nativeQuery = true)
    List<StatsDto> findOrderItemQuantityById(Integer restaurantId, Date start, Date end);


    @Query(value = "SELECT restaurant.id, item.id AS ItemId, order_item.quantity AS ItemQuantity, customer_order.ordering_time AS date FROM order_item\n" +
            "JOIN customer_order ON customer_order.order_item_id = order_item.id\n" +
            "JOIN item ON item.id = order_item.item_id\n" +
            "JOIN employee ON employee.id = customer_order.employee_id\n" +
            "JOIN restaurant ON restaurant.id = employee.restaurant_id\n" +
            "WHERE restaurant.id = ?1 AND item.id = ?2\n" +
            "AND customer_order.ordering_time BETWEEN ?3 AND ?4", nativeQuery = true)
    List<StatsDto> findOrderItemQuantityByIdAndItemId(int restaurantId, int itemId, Date start, Date end);

    @Query(value = "SELECT restaurant.id AS restaurantId, COUNT(seat_id) AS numOfGuests, CAST (customer_order.ordering_time AS DATE) AS date FROM customer_order\n" +
            "JOIN employee ON employee.id = customer_order.employee_id\n" +
            "JOIN restaurant ON restaurant.id = employee.restaurant_id\n" +
            "WHERE restaurant.id = ?1\n" +
            "AND customer_order.ordering_time BETWEEN ?2 AND ?3\n" +
            "GROUP BY (restaurant.id, date)", nativeQuery = true)
    List<StatsDto> findNumOfGuests(Integer restaurantId, Date start, Date end);
}
