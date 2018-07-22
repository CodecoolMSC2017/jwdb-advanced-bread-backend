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
        return restaurantRepository.findByIdAndOwnerId(restaurantId, ownerId);
    }

    public Set<Restaurant> getAllByOwnerId(int ownerId) {
        Set<Restaurant> restaurants= restaurantRepository.findByOwnerId(ownerId);
        if (restaurants != null) {
            return restaurants;
        } else {
            throw new RestaurantNotFoundException();
        }
    }

    @Override
    public Restaurant add(Restaurant restaurant, int ownerId) {
        addressRepository.save(restaurant.getAddress());
        restaurant.setOwner(ownerService.getOwnerById(ownerId));
        ownerService.getOwnerById(ownerId).getRestaurants().add(restaurant);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @Override
    public Restaurant edit(Restaurant restaurant, int ownerId) {
        restaurant.setOwner(ownerService.getOwnerById(ownerId));
        restaurantRepository.save(restaurant);
        return restaurant;
    }
}
