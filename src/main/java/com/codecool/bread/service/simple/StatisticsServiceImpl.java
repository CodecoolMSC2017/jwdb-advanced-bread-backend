package com.codecool.bread.service.simple;

import com.codecool.bread.model.dto.StatsDto;
import com.codecool.bread.service.StatisticsService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatisticsServiceImpl extends AbstractService implements StatisticsService {

    @Override
    public List<StatsDto> getAllRestaurantIncomeAvgFromDb(int ownerId, Date start, Date end) {
        return invoiceRepository.findInvoiceAvgByOwnerId(ownerId, start, end);
    }

    @Override
    public List<StatsDto> getAllRestaurantIncomeSumFromDb(int ownerId, Date start, Date end) {
        return invoiceRepository.findInvoiceSumByOwnerId(ownerId, start, end);
    }

    @Override
    public List<StatsDto> getAllOrderQuantityByFromDb(int restaurantId, Date start, Date end) {
        return customerOrderRepository.findOrderItemQuantityById(restaurantId, start, end);
    }

    public List<StatsDto> getOrderQuantityByItemIdFromDb(int restaurantId,int itemId, Date start, Date end) {
        return customerOrderRepository.findOrderItemQuantityByIdAndItemId(restaurantId,itemId, start, end);
    }

    @Override
    public List<StatsDto> getNumOfGuestsFromDb(int restaurantId, Date start, Date end) {
        return customerOrderRepository.findNumOfGuests(restaurantId, start, end);
    }
}
