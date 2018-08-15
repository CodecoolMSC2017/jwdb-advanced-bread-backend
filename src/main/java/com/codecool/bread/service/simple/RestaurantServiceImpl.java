package com.codecool.bread.service.simple;

import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.*;
import com.codecool.bread.repository.AddressRepository;
import com.codecool.bread.repository.RestaurantRepository;
import com.codecool.bread.repository.SeatRepository;
import com.codecool.bread.repository.TableRepository;
import com.codecool.bread.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("restaurantService")
public class RestaurantServiceImpl extends AbstractService implements RestaurantService {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private TableService tableService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SeatService seatService;

    @Override
    public Restaurant getById(int restaurantId, Principal principal) throws RestaurantAccessDeniedException, RestaurantNotFoundException {
        Restaurant restaurant = returnRestaurant(principal, restaurantId);
        if(restaurant == null || restaurant.isEnabled() == false) {
            throw new RestaurantNotFoundException();
        }
        return restaurant;
    }

    public Restaurant getById(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (!restaurant.isPresent()) {
            throw new RestaurantNotFoundException();
        }
        return restaurant.get();
    }

    public Set<Restaurant> getAllByOwnerId(int ownerId) {
        Set<Restaurant> restaurants = restaurantRepository.findByOwnerId(ownerId);
        if (restaurants.isEmpty()) {
            throw new RestaurantNotFoundException();
        } else {
            return restaurants;
        }
    }

    public Set<Restaurant> getAllEnableByOwnerId(int ownerId) throws RestaurantNotFoundException {
        Set<Restaurant> enableRestaurants = restaurantRepository.findByOwnerIdAndEnabledTrue(ownerId);
        if (enableRestaurants.isEmpty()) {
            throw new RestaurantNotFoundException();
        } else {
            return enableRestaurants;
        }
    }

    @Override
    public Restaurant add(Restaurant restaurant, int ownerId) {
        addressRepository.saveAndFlush(restaurant.getAddress());
        restaurant.setOwner(ownerService.getOwnerById(ownerId));
        ownerService.getOwnerById(ownerId).getRestaurants().add(restaurant);
        return restaurantRepository.saveAndFlush(restaurant);
    }

    @Override
    public Restaurant edit(Restaurant restaurant, Principal principal) throws RestaurantNotFoundException, RestaurantAccessDeniedException {
        int restaurantId = restaurant.getId();
        Owner owner = null;

        if(isOwner(principal)) {
            owner = ownerService.getOwnerByUsername(principal.getName());
        } else if(isManager(principal,restaurantId)) {
            owner = ownerService.getOwnerByRestaurantId(restaurantId);
        } else {
            throw new RestaurantAccessDeniedException();
        }
        restaurant.setOwner(owner);
        return restaurantRepository.saveAndFlush(restaurant);



    }

    @Override
    public void deleteRestaurant(int restaurantId) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (!restaurant.isPresent()) {
            throw new RestaurantNotFoundException();
        }
        employeeService.setAllEmployeeRestaurantNull(restaurant.get().getOwner().getId());
        tableService.deleteAllTableByRestaurantId(restaurantId);
        Set<Table> tables = restaurant.get().getTables();
        for(Table table : tables) {
            seatService.deleteAllSeatsByTableId(table);
        }
        restaurant.get().setEnabled(false);
        restaurantRepository.saveAndFlush(restaurant.get());
    }

    private Restaurant returnRestaurant(Principal principal, int restaurantId) throws RestaurantAccessDeniedException {
        Restaurant restaurant = null;

        if(isOwner(principal)) {
            int ownerId = ownerService.getOwnerByUsername(principal.getName()).getId();
            restaurant = restaurantRepository.findByIdAndOwnerId(restaurantId, ownerId);
        }
        if(isManager(principal, restaurantId)) {
            restaurant = restaurantRepository.findById(restaurantId).get();
            System.out.println(restaurant.getName());
        }
        return restaurant;
    }
}
