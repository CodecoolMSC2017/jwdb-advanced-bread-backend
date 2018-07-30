package com.codecool.bread.repository;

import com.codecool.bread.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    Set<Seat> findByTableId(Integer id);

    Seat findByIdAndTableId(Integer seatId, Integer tableId);

    Set<Seat> findByTableIdAndEnabledTrue(Integer tableId);

    @Query(value = "SELECT seat.id, seat.active, seat.restaurant_table_id, seat.enabled FROM seat\n" +
            "JOIN customer_order ON customer_order.seat_id = seat.id\n" +
            "JOIN invoice ON invoice.id = customer_order.invoice_id\n" +
            "WHERE invoice.id = ?1", nativeQuery = true)
    Set<Seat> findByInvoiceId(Integer invoiceId);
}
