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

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("restaurantService")
public class RestaurantServiceImpl extends AbstractService implements RestaurantService {

    @Autowired
    private TableService tableService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SeatService seatService;

    @Override
    public Restaurant getById(int restaurantId, int ownerId) throws RestaurantAccessDeniedException, RestaurantNotFoundException {
        Optional<Restaurant> restaurant =restaurantRepository.findById(restaurantId);
        if(!restaurant.isPresent() || !restaurant.get().isEnabled()) {
            throw new RestaurantNotFoundException();
        }
        return restaurant.get();
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

    public Set<Restaurant> getAllEnableByEmployeeId(int employeeId) throws RestaurantNotFoundException {
        Set<Restaurant> enableRestaurants = restaurantRepository.findByEmployeesIdAndEnabledTrue(employeeId);
        if (enableRestaurants.isEmpty()) {
            throw new RestaurantNotFoundException();
        } else {
            return enableRestaurants;
        }
    }

    /**
     *
     * @param restaurantId
     * @param employeeId
     * @return Restaurant
     * @throws RestaurantNotFoundException
     * @throws RestaurantAccessDeniedException
     * Returns a Restaurant object if the given employeeId belongs to it
     */
    @Override
    public Restaurant getByIdAndAuthorizedEmployee(int restaurantId, int employeeId) throws RestaurantNotFoundException, RestaurantAccessDeniedException {
        Restaurant restaurant = getById(restaurantId);
        Employee employee = employeeService.getById(employeeId);
        if (restaurant.getOwner().equals(employee) || (restaurant.getEmployees().contains(employee) && employee.getRole().equals(Role.MANAGER))) {
            return restaurant;
        } else {
            throw new RestaurantAccessDeniedException();
        }
    }

    @Override
    public Restaurant add(Restaurant restaurant, int ownerId) {
        addressRepository.saveAndFlush(restaurant.getAddress());
        restaurant.setOwner(employeeService.getOwnerById(ownerId));
        return restaurantRepository.saveAndFlush(restaurant);
    }

    @Override
    public Restaurant edit(Restaurant restaurant, int employeeId, int restaurantId) throws RestaurantNotFoundException {
        if(getById(restaurant.getId()).getOwner().equals(employeeService.getById(employeeId)) && restaurant.getId() == restaurantId) {
            restaurant.setOwner(employeeService.getOwnerById(employeeId));
            return restaurantRepository.saveAndFlush(restaurant);
        } else {
            throw new RestaurantNotFoundException();
        }

    }

    @Override
    public void deleteRestaurant(int restaurantId) throws RestaurantNotFoundException {
        Restaurant restaurant = getById(restaurantId);
        employeeService.setAllEmployeeRestaurantNull(restaurant.getOwner().getId());
        tableService.deleteAllTableByRestaurantId(restaurantId);
        Set<Table> tables = restaurant.getTables();
        for(Table table : tables) {
            seatService.deleteAllSeatsByTableId(table);
        }
        restaurant.setEnabled(false);
        restaurantRepository.saveAndFlush(restaurant);
    }

    private Restaurant returnRestaurant(Principal principal, int restaurantId) throws RestaurantAccessDeniedException {
        Restaurant restaurant = null;

        if(isOwner(principal)) {
            int ownerId = ownerService.getOwnerByUsername(principal.getName()).getId();
            restaurant = restaurantRepository.findByIdAndOwnerId(restaurantId, ownerId);
        }
        if(isManager(principal, restaurantId)) {
            restaurant = restaurantRepository.findById(restaurantId).get();
        }
        return restaurant;
    }

    private Set<Restaurant> getAllEnabledRestaurants(Principal principal) {
        Set<Restaurant> restaurants = null;
        if(isOwner(principal)) {
            int ownerId = ownerService.getOwnerByUsername(principal.getName()).getId();
            restaurants = restaurantRepository.findByOwnerIdAndEnabledTrue(ownerId);
        } else if(isManager(principal)) {
            int restaurantId = employeeService.getByUsername(principal.getName()).getRestaurant().getId();
            restaurants = new HashSet<>(Arrays.asList(restaurantRepository.findById(restaurantId).get()));
        }
        return restaurants;
    }
}
