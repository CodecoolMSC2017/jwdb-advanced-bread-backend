package com.codecool.bread.repository;

import com.codecool.bread.model.CustomerOrder;
import com.codecool.bread.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {

    Optional<CustomerOrder> findByIdAndSeatId(Integer id, Integer seatId);

    Set<CustomerOrder> findBySeatIdAndEnabledTrue(Integer seatId);

    List<CustomerOrder> findByInvoiceId(int invoiceId);

    @Query(value = "SELECT * FROM customer_order WHERE order_item_id = 3 and invoice_id IS null", nativeQuery = true)
    CustomerOrder findByOrderItem(Integer orderItemId);
}
