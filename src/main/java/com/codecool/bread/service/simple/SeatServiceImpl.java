package com.codecool.bread.service.simple;

import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.exception.SeatNotFoundException;
import com.codecool.bread.exception.TableNotFoundException;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.Seat;
import com.codecool.bread.model.Table;
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

    @Override
    public Seat getSeatById(int tableId, int seatId) throws SeatNotFoundException {
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

}
