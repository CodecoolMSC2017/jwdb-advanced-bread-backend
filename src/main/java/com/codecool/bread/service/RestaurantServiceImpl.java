package com.codecool.bread.service;

import com.codecool.bread.exception.RestaurantAccessDeniedException;
import com.codecool.bread.exception.RestaurantNotFoundException;
import com.codecool.bread.model.Restaurant;
import com.codecool.bread.model.Table;
import com.codecool.bread.repository.RestaurantRepository;
import com.codecool.bread.repository.TableRepository;
import com.codecool.bread.service.simple.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private TableRepository tableRepository;


    public List<Restaurant> getRestaurantsByOwnerId(int id){
        return restaurantRepository.findByOwnerId(id);
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId, int ownerId) throws RestaurantAccessDeniedException, RestaurantNotFoundException {
        if(restaurantRepository.findById(restaurantId).isPresent()) {
            if(restaurantRepository.findByOwnerId(ownerId).contains(restaurantRepository.findById(restaurantId).get())) {
                return restaurantRepository.findById(restaurantId).get();
            } else {
                throw new RestaurantAccessDeniedException();
            }
        }else {
            throw new RestaurantNotFoundException();
        }
    }

    @Override
    public Set<Table> getAllTableByRestaurantIdFromDb(int restaurantId, int ownerId) {
        return getRestaurantById(restaurantId, ownerId).getTables();
    }

    @Override
    public Table getTableByIdFromDb(int tableId, int restaurantId, int ownerId) {
        return null;
    }
}
