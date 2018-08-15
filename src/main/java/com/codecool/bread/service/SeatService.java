package com.codecool.bread.service;

import com.codecool.bread.exception.NoSeatsFoundException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.exception.SeatNotFoundException;
import com.codecool.bread.model.Seat;
import com.codecool.bread.model.Table;

import java.util.List;
import java.util.Set;

public interface SeatService {

    List<Seat> addMultipleSeats(int value, int tableId) throws RestaurantNotFoundException;

    Seat add(Seat seat, int restaurantId) throws RestaurantNotFoundException;

    Set<Seat> getAllSeatsByTableId(int tableId);

    Set<Seat> getEnableSeatsByTableId(int tableId) throws NoSeatsFoundException;

    Seat getSeatByIdAndTableId(int tableId, int seatId);

    Seat getEnableSeatById(int tableId, int seatId) throws SeatNotFoundException;

    Seat edit(Seat seat, int tableId);

    void deleteSeat(int seatId, int tableId) throws SeatNotFoundException;

    void deleteAllSeatsByTableId(Table table);

    Seat getById(int seatId) throws SeatNotFoundException;
}
