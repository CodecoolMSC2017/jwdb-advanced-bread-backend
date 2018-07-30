package com.codecool.bread.service.simple;

import com.codecool.bread.exception.NoSeatsFoundException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.exception.SeatNotFoundException;
import com.codecool.bread.exception.TableNotFoundException;
import com.codecool.bread.model.Seat;
import com.codecool.bread.model.Table;
import com.codecool.bread.repository.CustomerOrderRepository;
import com.codecool.bread.repository.SeatRepository;
import com.codecool.bread.repository.TableRepository;
import com.codecool.bread.service.SeatService;
import com.codecool.bread.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private TableService tableService;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private TableRepository tableRepository;

    @Override
    public Set<Seat> getAllSeatsByTableId(int tableId) {
        return seatRepository.findByTableId(tableId);
    }

    public Set<Seat> getEnableSeatsByTableId(int tableId) throws NoSeatsFoundException {
        Set<Seat> enableSeats = seatRepository.findByTableIdAndEnabledTrue(tableId);
        if(enableSeats.isEmpty()) {
            throw new NoSeatsFoundException();
        }
        return enableSeats;
    }

    @Override
    public Seat getSeatByIdAndTableId(int tableId, int seatId) throws SeatNotFoundException {
        Set<Seat> seats = seatRepository.findByTableId(tableId);
        Optional<Seat> seat = seatRepository.findById(seatId);
        if (!seat.isPresent()) {
            throw new SeatNotFoundException();
        }
        if(seats.contains(seat.get())){
            return seat.get();
        }
        throw new SeatNotFoundException();
    }

    public Seat getEnableSeatById(int tableId, int seatId) throws SeatNotFoundException {
        Set<Seat> seats = seatRepository.findByTableIdAndEnabledTrue(tableId);
        Optional<Seat> seat = seatRepository.findById(seatId);
        if (!seat.isPresent()) {
            throw new SeatNotFoundException();
        }
        if(seats.contains(seat.get())){
            return seat.get();
        }
        throw new SeatNotFoundException();
    }

    @Override
    public Seat add(Seat seat, int tableId) throws RestaurantNotFoundException {
        Optional<Table> table = tableRepository.findById(tableId);
        if (!table.isPresent()) {
            throw new RestaurantNotFoundException();
        }
        table.get().getSeats().add(seat);
        seat.setTable(table.get());
        seatRepository.save(seat);
        return seat;
    }

    @Override
    public Seat edit(Seat seat, int tableId) throws TableNotFoundException {
        Optional<Table> table = tableRepository.findById(tableId);
        if (!table.isPresent()) {
            throw new TableNotFoundException();
        }
        seat.setTable(table.get());
        return seatRepository.save(seat);
    }

    @Override
    public void deleteSeat(int seatId, int tableId) throws SeatNotFoundException {
        Seat seat = seatRepository.findByIdAndTableId(seatId, tableId);
        if(seat == null) {
            throw new SeatNotFoundException();
        }
        seat.setEnabled(false);
        seatRepository.saveAndFlush(seat);
    }

    @Override
    public void deleteSeatsForTable(Set<Seat> seats) {
        for(Seat seat : seats) {
            seat.setEnabled(false);
            seatRepository.saveAndFlush(seat);
        }
    }

    @Override
    public Seat getById(int seatId) throws SeatNotFoundException {
        Optional<Seat> seat = seatRepository.findById(seatId);
        if (seat.isPresent()) {
            return seat.get();
        } else {
            throw new SeatNotFoundException();
        }
    }
}
