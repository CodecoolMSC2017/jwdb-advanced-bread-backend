package com.codecool.bread.controller;

import com.codecool.bread.exception.NoSeatsFoundException;
import com.codecool.bread.exception.SeatNotFoundException;
import com.codecool.bread.model.Seat;
import com.codecool.bread.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/table/{id}/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("")
    public Set<Seat> getAllSeatsByRestaurantId(@PathVariable("restaurantId") int restaurantId, @PathVariable("tableId") int tableId) {
        Set<Seat> seats = seatService.getAllSeatByTableId(restaurantId, tableId);
        if(seats.size() != 0){
            return seats;
        }else
            throw new NoSeatsFoundException();
    }

    @GetMapping("/{seatId}")
    public Seat getSeatById(@PathVariable("restaurantId") int restaurantId, @PathVariable("tableId") int tableId, @PathVariable("seatId") int seatId) {
        Seat seat = seatService.getSeatById(restaurantId, tableId, seatId);
        Set<Seat> seats = seatService.getAllSeatByTableId(restaurantId, tableId);
        if(seats.contains(seat)){
            return seat;
        }
        throw new SeatNotFoundException();
    }

    @PostMapping("")
    public Seat addSeat(@RequestBody Seat seat, @PathVariable("tableId") int tableId, @PathVariable("restaurantId") int restaurantId) {
        return seatService.addOrModifySeat(seat, restaurantId, tableId);
    }

    @PutMapping("/{seatId}")
    public Seat modifySeat(@RequestBody Seat seat, @PathVariable("tableId") int tableId, @PathVariable("restaurantId") int restaurantId) {
        return seatService.addOrModifySeat(seat, restaurantId, tableId);
    }
}
