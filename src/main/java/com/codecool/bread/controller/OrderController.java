package com.codecool.bread.controller;

import com.codecool.bread.model.CustomerOrder;
import com.codecool.bread.model.Invoice;
import com.codecool.bread.model.OrderItem;
import com.codecool.bread.model.dto.OrderDto;
import com.codecool.bread.model.dto.TableDto;
import com.codecool.bread.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("order/")
public class OrderController extends AbstractController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/seat/{seatId}/")
    public Set<CustomerOrder> getAllCustomerOrder(@PathVariable("seatId") int seatId) {
        return orderService.getAllCustomerOrderBySeat(seatId);
    }

    @GetMapping("/table/{tableId}")
    public TableDto getActiveOrdersByTable(@PathVariable("tableId") int tableId) {
        return orderService.getActiveOrdersByTable(tableId);
    }

    @GetMapping("/seat/{seatId}/{customerOrderId}")
    public CustomerOrder getCustomerOrderById(@PathVariable("seatId") int seatId,
                                          @PathVariable("customerOrderId") int customerOrderId) {
        return orderService.getCustomerOrderById(seatId, customerOrderId);
    }

    @GetMapping("/seat/{seatId}/{customerOrderId}/order-item")
    public OrderItem getOrderItem(@PathVariable("seatId") int seatId,
                                  @PathVariable("customerOrderId") int customerOrderId) {
        return orderService.getOrderItem(seatId, customerOrderId);
    }

    @PostMapping("seat/{seatId}/")
    public OrderItem addOrder(@RequestBody OrderDto orderDto,
                             @PathVariable("seatId") int seatId,
                             Principal principal) {
        int loggedInEmployeeId = getLoggedInEmployeeId(principal);
        return orderService.add(orderDto,  seatId, loggedInEmployeeId);
    }

    @GetMapping("/table/{tableId}/invoice")
    public Invoice getInvoiceForTable(@PathVariable("tableId") int tableId,
                              Principal principal) {
        return orderService.generateInvoiceForTable(tableId, principal);
    }
}
