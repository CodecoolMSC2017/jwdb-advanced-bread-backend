package com.codecool.bread.service.simple;

import com.codecool.bread.model.Employee;
import com.codecool.bread.model.Owner;
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


    protected boolean isOwner(Principal principal, int restaurantId) {
        Optional<Owner> owner = ownerRepository.findByRestaurantId(restaurantId);
        if(principal.getName().equals(owner.get().getUser().getUsername())) {
            return true;
        }
        return false;
    }

    protected boolean isOwner(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        if (user.getAuthorities().contains("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }

    protected boolean isManager(Principal principal, int restaurantId) {
        User user = userRepository.findByUsername(principal.getName()).get();
        if (!user.getAuthorities().contains("ROLE_USER")) {
            return false;
        }
        Employee employee = employeeRepository.findByUserId(user.getId()).get();
        int employeeRestaurant = employee.getRestaurant().getId();
        if (employee.getRole().equals(Role.MANAGER) && employeeRestaurant == restaurantId) {
            return true;
        }
        return false;
    }

    protected boolean isManager(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        if (!user.getAuthorities().contains("ROLE_USER")) {
            return false;
        }
        return true;

    }

}

