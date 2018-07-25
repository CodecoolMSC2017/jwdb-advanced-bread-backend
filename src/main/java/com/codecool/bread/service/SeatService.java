package com.codecool.bread.service;

import com.codecool.bread.exception.SeatNotFoundException;
import com.codecool.bread.model.Seat;

import java.util.Set;

public interface SeatService {

    Seat add(Seat seat, int restaurantId);

    Set<Seat> getAllSeatsByTableId(int tableId);

    Seat getSeatById(int tableId, int seatId);

    Seat edit(Seat seat, int tableId);

    void deleteSeat(int seatId, int tableId) throws SeatNotFoundException;
}
