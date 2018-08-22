package com.codecool.bread.service.simple;

import com.codecool.bread.model.Employee;
import com.codecool.bread.model.Role;
import com.codecool.bread.model.User;
import com.codecool.bread.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Optional;

public class AbstractService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CustomerOrderRepository customerOrderRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    TableRepository tableRepository;

    @Autowired
    UserRepository userRepository;

}

