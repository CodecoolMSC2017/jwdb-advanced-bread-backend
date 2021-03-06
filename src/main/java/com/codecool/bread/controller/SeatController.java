package com.codecool.bread.controller;

import com.codecool.bread.exception.IdMismatchException;
import com.codecool.bread.exception.NoSeatsFoundException;
import com.codecool.bread.model.Seat;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/table/{tableId}/seat")
public class SeatController extends AbstractController {

    @GetMapping("")
    public Set<Seat> getAllSeatsByRestaurantId(@PathVariable("tableId") int tableId) {
        Set<Seat> seats = seatService.getEnableSeatsByTableId(tableId);
        if(seats.size() != 0){
            return seats;
        }else
            throw new NoSeatsFoundException();
    }

    @GetMapping("/{seatId}")
    public Seat getSeatById(@PathVariable("tableId") int tableId,
                            @PathVariable("seatId") int seatId) {
        return  seatService.getEnableSeatById(tableId, seatId);
    }

    @PostMapping("")
    public List<Seat> addMultipleSeats(@RequestBody Map<String, String> map,
                          @PathVariable("tableId") int tableId) {
        int value = Integer.parseInt(map.get("value"));
        return seatService.addMultipleSeats(value, tableId);
    }

    @PutMapping("/{seatId}")
    public Seat edit(@RequestBody Seat seat,
                     @PathVariable("tableId") int tableId,
                     @PathVariable("seatId") int seatId) {
        if (checkIdMatch(seat, seatId)) {
            return seatService.add(seat, tableId);
        } else {
            throw new IdMismatchException();
        }
    }

    @DeleteMapping("/{seatId}")
    public void deleteSeat(@PathVariable("tableId") int tableId,
                           @PathVariable("seatId") int seatId) {
        seatService.deleteSeat(seatId, tableId);
    }
}
