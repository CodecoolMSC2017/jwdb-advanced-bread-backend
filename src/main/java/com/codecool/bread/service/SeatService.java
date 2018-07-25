package com.codecool.bread.service;

import com.codecool.bread.exception.NoSeatsFoundException;
import com.codecool.bread.exception.SeatNotFoundException;
import com.codecool.bread.model.Seat;

import java.util.Set;

public interface SeatService {

    Seat add(Seat seat, int restaurantId);

    Set<Seat> getAllSeatsByTableId(int tableId);

    Set<Seat> getEnableSeatsByTableId(int tableId) throws NoSeatsFoundException;

    Seat getSeatById(int tableId, int seatId);

    Seat getEnableSeatById(int tableId, int seatId) throws SeatNotFoundException;

    Seat edit(Seat seat, int tableId);

    void deleteSeat(int seatId, int tableId) throws SeatNotFoundException;

    void deleteSeatsForTable(Set<Seat> seats);
}
