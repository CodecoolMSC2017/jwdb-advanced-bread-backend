package com.codecool.bread.service.simple;

import com.codecool.bread.model.dto.StatsDto;
import com.codecool.bread.service.StatisticsService;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl extends AbstractService implements StatisticsService {

    @Override
    public StatsDto getAllRestaurantIncomeAvgFromDb(int ownerId, int year, int month) {
        return new StatsDto(invoiceRepository.findInvoiceSumsByEmployeeId(ownerId, year, month));
    }
}
