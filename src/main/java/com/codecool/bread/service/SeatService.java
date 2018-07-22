package com.codecool.bread.service;

import com.codecool.bread.model.Seat;

import java.util.Set;

public interface SeatService {

    Set<Seat> getAllSeatByTableIdFromDb(int restaurantId, int tableId);

    Seat getSeatByIdFromDb(int restaurantId, int tableId, int seatId);

    Seat addOrModifySeatToDb(Seat seat, int restaurantId, int tableId);
}
