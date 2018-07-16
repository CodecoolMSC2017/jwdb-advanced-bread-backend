package com.codecool.bread.repository;

import com.codecool.bread.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByTableId(int id);
    Seat findByidAndTableId(Integer seatId, Integer tableId);
}
