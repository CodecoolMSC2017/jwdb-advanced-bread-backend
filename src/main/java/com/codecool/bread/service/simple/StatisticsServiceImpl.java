package com.codecool.bread.service.simple;

import com.codecool.bread.model.dto.StatsDto;
import com.codecool.bread.service.StatisticsService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StatisticsServiceImpl extends AbstractService implements StatisticsService {

    @Override
    public StatsDto getAllRestaurantIncomeAvgFromDb(int ownerId, Date start, Date end) {
        StatsDto statsDto = new StatsDto();
        statsDto.setOwnerId(ownerId);
        statsDto.setAllIncomeAvg(invoiceRepository.findInvoiceAvgByOwnerId(ownerId, start, end));
        return statsDto;
    }

    @Override
    public StatsDto getRestaurantIncomeAvgFromDb(int restaurantId, Date start, Date end) {
        StatsDto statsDto = new StatsDto();
        statsDto.setRestaurantId(restaurantId);
        statsDto.setIncomeAvg(invoiceRepository.findInvoiceAvgByRestaurantId(restaurantId, start, end));
        return statsDto;
    }

    @Override
    public StatsDto getAllRestaurantIncomeSumFromDb(int ownerId, Date start, Date end) {
        StatsDto statsDto = new StatsDto();
        statsDto.setOwnerId(ownerId);
        statsDto.setAllIncomeSum(invoiceRepository.findInvoiceSumByOwnerId(ownerId, start, end));
        return statsDto;
    }

    @Override
    public StatsDto getRestaurantIncomeSumFromDb(int restaurantId, Date start, Date end) {
        StatsDto statsDto = new StatsDto();
        statsDto.setRestaurantId(restaurantId);
        statsDto.setIncomeSum(invoiceRepository.findInvoiceSumByRestaurantId(restaurantId, start, end));
        return statsDto;
    }

    @Override
    public StatsDto getOrderQuantityByItemIdFromDb(int orderItemId, Date start, Date end) {
        StatsDto statsDto = new StatsDto();
        statsDto.setOrderItemId(orderItemId);
        statsDto.setOrderItemQuantity(customerOrderRepository.findOrderItemQuantityById(orderItemId, start, end));
        return statsDto;
    }
}
