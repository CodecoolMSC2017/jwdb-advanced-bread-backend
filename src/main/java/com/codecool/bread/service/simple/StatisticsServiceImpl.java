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
    public StatsDto getRestaurantIncomeAvgFromDb(int restaurantId, Date start, Date end) {
        return null;
    }

    @Override
    public List<StatsDto> getAllRestaurantIncomeSumFromDb(int ownerId, Date start, Date end) {
        return invoiceRepository.findInvoiceSumByOwnerId(ownerId, start, end);
    }

    @Override
    public StatsDto getRestaurantIncomeSumFromDb(int restaurantId, Date start, Date end) {
        return null;
    }

    @Override
    public List<StatsDto> getOrderQuantityByItemIdFromDb(int restaurantId, Date start, Date end) {
        return customerOrderRepository.findOrderItemQuantityById(restaurantId, start, end);
    }
}
