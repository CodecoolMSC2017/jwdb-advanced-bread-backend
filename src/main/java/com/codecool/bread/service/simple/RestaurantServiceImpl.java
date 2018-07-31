package com.codecool.bread.service.simple;

import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.repository.AddressRepository;
import com.codecool.bread.repository.RestaurantRepository;
import com.codecool.bread.repository.SeatRepository;
import com.codecool.bread.repository.TableRepository;
import com.codecool.bread.service.OwnerService;
import com.codecool.bread.service.RestaurantService;
import com.codecool.bread.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private TableService tableService;

    @Override
    public Restaurant getById(int restaurantId, int ownerId) throws RestaurantAccessDeniedException, RestaurantNotFoundException {
        Restaurant restaurant =restaurantRepository.findByIdAndOwnerId(restaurantId, ownerId);
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
    public Restaurant edit(Restaurant restaurant, int ownerId) throws RestaurantNotFoundException {
        restaurant.setOwner(ownerService.getOwnerById(ownerId));
        return restaurantRepository.saveAndFlush(restaurant);
    }

    @Override
    public void deleteRestaurant(int restaurantId) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (!restaurant.isPresent()) {
            throw new RestaurantNotFoundException();
        }
        restaurant.get().setEnabled(false);
        restaurantRepository.saveAndFlush(restaurant.get());
    }
}
