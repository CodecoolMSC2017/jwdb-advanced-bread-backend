package com.codecool.bread.controller;

import com.codecool.bread.model.dto.StatsDto;
import com.codecool.bread.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatisticsController extends AbstractController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/owner/restaurants/avg/income")
    public List<StatsDto> getAllRestaurantIncomeAvg(Principal principal, @RequestParam("start")
    @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return statisticsService.getAllRestaurantIncomeAvgFromDb(getLoggedInEmployeeId(principal), start, end);
    }

    @GetMapping("/owner/restaurants/sum/income")
    public List<StatsDto> getAllRestaurantIncomeSum(Principal principal, @RequestParam("start")
    @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return statisticsService.getAllRestaurantIncomeSumFromDb(getLoggedInEmployeeId(principal), start, end);
    }

    @GetMapping("/order/orderitemquantity/{restaurantId}")
    public List<StatsDto> getOrderQuantity(@PathVariable("restaurantId") int restaurantId,
                                           @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return statisticsService.getOrderQuantityByItemIdFromDb(restaurantId, start, end);
    }

    @GetMapping("/numofguests/{restaurantId}")
    public List<StatsDto> getNumOfGuests(@PathVariable("restaurantId") int restaurantId,
                                         @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return statisticsService.getNumOfGuestsFromDb(restaurantId, start, end);
    }
}
