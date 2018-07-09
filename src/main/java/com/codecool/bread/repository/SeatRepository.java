package com.codecool.bread.repository;

import com.codecool.bread.model.Seat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Integer> {
}
