package com.codecool.bread.service.simple;

import com.codecool.bread.model.Seat;
import com.codecool.bread.model.Table;
import com.codecool.bread.repository.SeatRepository;
import com.codecool.bread.repository.TableRepository;
import com.codecool.bread.service.SeatService;
import com.codecool.bread.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Set<Seat> getAllSeatByTableIdFromDb(int restaurantId, int tableId) {
        return tableService.getTableByIdFromDb(tableId, restaurantId).getSeats();
    }

    @Override
    public Seat getSeatByIdFromDb(int restaurantId, int tableId, int seatId) {
        return seatRepository.findByIdAndTableId(seatId, tableId);
    }

    @Override
    public Seat addOrModifySeatToDb(Seat seat, int restaurantId, int tableId) {
        Table table = tableRepository.findByIdAndRestaurantId(tableId, restaurantId);
        table.getSeats().add(seat);
        seat.setTable(table);
        seatRepository.save(seat);
        return seat;
    }
}
