package com.codecool.bread.controller;

import com.codecool.bread.model.CustomerOrder;
import com.codecool.bread.model.Invoice;
import com.codecool.bread.model.OrderItem;
import com.codecool.bread.model.dto.*;
import com.codecool.bread.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/order")
public class OrderController extends AbstractController {

    @GetMapping("/seat/{seatId}")
    public List<CustomerOrder> getAllCustomerOrderBySeat(@PathVariable("seatId") int seatId) {
        return orderService.getAllCustomerOrderBySeat(seatId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public RestaurantDto getActiveOrdersByRestaurant(@PathVariable("restaurantId") int restaurantId) {
        return orderService.getActiveOrdersByRestaurant(restaurantId);
    }

    @GetMapping("/table/{tableId}")
    public TableDto getActiveOrdersByTable(@PathVariable("tableId") int tableId) {
        return orderService.getActiveOrdersByTable(tableId);
    }

    @GetMapping("/seat/{seatId}/active")
    public SeatDto getActiveOrdersBySeat(@PathVariable("seatId") int seatId) {
        return orderService.getActiveOrdersBySeat(seatId);
    }

    @GetMapping("/seat/{seatId}/{customerOrderId}")
    public CustomerOrder getCustomerOrderById(@PathVariable("seatId") int seatId,
                                          @PathVariable("customerOrderId") int customerOrderId) {
        return orderService.getCustomerOrderById(seatId, customerOrderId);
    }

    @GetMapping("/table/{tableId}/invoice")
    public InvoiceDto createInvoiceForTable(@PathVariable("tableId") int tableId, Principal principal) {

        return orderService.createInvoiceForTable(tableId, getLoggedInEmployeeId(principal));
    }
/*
    @GetMapping("/seat/{seatId}/invoice")
    public Invoice createInvoiceForSeat(@PathVariable("seatId") int seatId) {
        return orderService.createInvoiceForSeat(seatId);
    }
*/
    @GetMapping("/invoice")
    public Invoice createInvoiceForSeats(@RequestParam("seatId") int[] seatIds) {
        return orderService.createInvoiceForSeats(seatIds);
    }

    @GetMapping("/seat/{seatId}/{customerOrderId}/order-item")
    public OrderItem getOrderItem(@PathVariable("seatId") int seatId,
                                  @PathVariable("customerOrderId") int customerOrderId) {
        return orderService.getOrderItem(seatId, customerOrderId);
    }

    @PutMapping("/invoice/set-paid")
    public void setInvoiceAsPaid(@RequestBody InvoiceDto invoiceDto) {
        int invoiceId = invoiceDto.getId();
        orderService.setInvoiceAsPaid(invoiceId);

    }

    @PostMapping("/seat/{seatId}")
    public OrderItem addOrder(@RequestBody OrderDto orderDto,
                             @PathVariable("seatId") int seatId,
                             Principal principal) {
        int loggedInEmployeeId = getLoggedInEmployeeId(principal);
        return orderService.add(orderDto,  seatId, loggedInEmployeeId);
    }

    @DeleteMapping("/seat/{seatId}")
    public void deleteOrderFromSeat(@PathVariable("seatId") int seatId,
                                    @RequestParam("orderItemId") int orderItemId) {
        orderService.deleteOrderFromSeat(seatId, orderItemId);
    }
}
