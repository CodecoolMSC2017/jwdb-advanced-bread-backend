package com.codecool.bread.service;

import com.codecool.bread.model.Seat;

import java.util.Set;

public interface SeatService {

    Set<Seat> getAllSeatByTableId(int restaurantId, int tableId);

    Seat getSeatById(int restaurantId, int tableId, int seatId);

    Seat addOrModifySeat(Seat seat, int restaurantId, int tableId);
}
