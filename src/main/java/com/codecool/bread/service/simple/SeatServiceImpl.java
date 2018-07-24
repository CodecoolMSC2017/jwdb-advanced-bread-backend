package com.codecool.bread.service.simple;

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
    public Set<Seat> getAllSeatByTableId(int restaurantId, int tableId) {
        return tableService.getTableById(tableId, restaurantId).getSeats();
    }

    @Override
    public Seat getSeatById(int restaurantId, int tableId, int seatId) {
        return seatRepository.findByIdAndTableId(seatId, tableId);
    }

    @Override
    public Seat addOrModifySeat(Seat seat, int restaurantId, int tableId) {
        Optional<Table> table = tableRepository.findByIdAndRestaurantId(tableId, restaurantId);
        table.get().getSeats().add(seat);
        seat.setTable(table.get());
        seatRepository.save(seat);
        return seat;
    }
}
