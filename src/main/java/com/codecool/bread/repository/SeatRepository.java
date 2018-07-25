package com.codecool.bread.repository;

import com.codecool.bread.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    Set<Seat> findByTableId(Integer id);

    Seat findByIdAndTableId(Integer seatId, Integer tableId);
}
