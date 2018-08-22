package com.codecool.bread.service.simple;

import com.codecool.bread.model.dto.StatsDto;
import com.codecool.bread.service.StatisticsService;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl extends AbstractService implements StatisticsService {

    @Override
    public StatsDto getAllRestaurantIncomeAvgFromDb(int ownerId, int year, int month) {
        StatsDto statsDto = new StatsDto();
        statsDto.setAllIncomeAvg(invoiceRepository.findInvoiceAvgByOwnerId(ownerId, year, month));
        return statsDto;
    }

    @Override
    public StatsDto getRestaurantIncomeAvgFromDb(int restaurantId, int year, int month) {
        StatsDto statsDto = new StatsDto();
        statsDto.setRestaurantId(restaurantId);
        statsDto.setIncomeAvg(invoiceRepository.findInvoiceAvgByRestaurantId(restaurantId, year, month));
        return statsDto;
    }

    @Override
    public StatsDto getAllRestaurantIncomeSumFromDb(int ownerId, int year, int month) {
        StatsDto statsDto = new StatsDto();
        statsDto.setAllIncomeSum(invoiceRepository.findInvoiceSumByOwnerId(ownerId, year, month));
        return statsDto;
    }

    @Override
    public StatsDto getRestaurantIncomeSumFromDb(int restaurantId, int year, int month) {
        StatsDto statsDto = new StatsDto();
        statsDto.setRestaurantId(restaurantId);
        statsDto.setIncomeSum(invoiceRepository.findInvoiceSumByRestaurantId(restaurantId, year, month));
        return statsDto;
    }
}
