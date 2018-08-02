package com.codecool.bread.repository;

import com.codecool.bread.model.CustomerOrder;
import com.codecool.bread.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {

    Optional<CustomerOrder> findByIdAndSeatId(Integer id, Integer seatId);

    Set<CustomerOrder> findBySeatIdAndEnabledTrue(Integer seatId);

    Set<CustomerOrder> findByInvoiceId(int invoiceId);
}
