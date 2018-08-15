package com.codecool.bread.controller;

import com.codecool.bread.model.dto.StatsDto;
import com.codecool.bread.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stats")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/owner/{ownerId}/restaurants/avg/income")
    public StatsDto getAllRestaurantIncomeAvg(@PathVariable("ownerId") int ownerId, @RequestParam("year") int year, @RequestParam("month") int month) {
        return statisticsService.getAllRestaurantIncomeAvgFromDb(ownerId, year, month);
    }

    @GetMapping("/owner/restaurant/{restaurantId}/avg/income")
    public StatsDto getRestaurantIncome(@PathVariable("restaurantId") int restaurantId, @RequestParam("year") int year, @RequestParam("month") int month) {
        return statisticsService.getRestaurantIncomeAvgFromDb(restaurantId, year, month);
    }
}
