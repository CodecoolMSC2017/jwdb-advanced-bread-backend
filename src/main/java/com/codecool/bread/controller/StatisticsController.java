package com.codecool.bread.controller;

import com.codecool.bread.model.dto.StatsDto;
import com.codecool.bread.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/owner/{ownerId}/restaurants/avg/income")
    public List<StatsDto> getAllRestaurantIncomeAvg(@PathVariable("ownerId") int ownerId, @RequestParam("start")
    @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return statisticsService.getAllRestaurantIncomeAvgFromDb(ownerId, start, end);
    }

    @GetMapping("/owner/restaurant/{restaurantId}/avg/income")
    public StatsDto getRestaurantIncomeAvg(@PathVariable("restaurantId") int restaurantId, @RequestParam("start")
    @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return statisticsService.getRestaurantIncomeAvgFromDb(restaurantId, start, end);
    }

    @GetMapping("/owner/{ownerId}/restaurants/sum/income")
    public List<StatsDto> getAllRestaurantIncomeSum(@PathVariable("ownerId") int ownerId, @RequestParam("start")
    @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return statisticsService.getAllRestaurantIncomeSumFromDb(ownerId, start, end);
    }

    @GetMapping("/owner/restaurant/{restaurantId}/sum/income")
    public StatsDto getRestaurantIncomeSum(@PathVariable("restaurantId") int restaurantId, @RequestParam("start")
    @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return statisticsService.getRestaurantIncomeSumFromDb(restaurantId, start, end);
    }

    @GetMapping("/order/orderitemquantity/{orderItemId}")
    public List<StatsDto> getOrderQuantityByItemId(@PathVariable("orderItemId") int orderItemId,
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return statisticsService.getOrderQuantityByItemIdFromDb(orderItemId, start, end);
    }
}
