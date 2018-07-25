package com.codecool.bread.controller;

import com.codecool.bread.exception.NoSeatsFoundException;
import com.codecool.bread.exception.SeatNotFoundException;
import com.codecool.bread.model.Seat;
import com.codecool.bread.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/table/{tableId}/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("")
    public Set<Seat> getAllSeatsByRestaurantId(@PathVariable("tableId") int tableId) {
        Set<Seat> seats = seatService.getAllSeatsByTableId(tableId);
        if(seats.size() != 0){
            return seats;
        }else
            throw new NoSeatsFoundException();
    }

    @GetMapping("/{seatId}")
    public Seat getSeatById(@PathVariable("tableId") int tableId,
                            @PathVariable("seatId") int seatId) {
        return  seatService.getSeatById(tableId, seatId);
    }

    @PostMapping("")
    public Seat add(@RequestBody Seat seat,
                        @PathVariable("tableId") int tableId) {
        return seatService.add(seat, tableId);
    }

    @PutMapping("/{seatId}")
    public Seat edit(@RequestBody Seat seat,
                           @PathVariable("tableId") int tableId) {
        return seatService.edit(seat,  tableId);
    }
}
