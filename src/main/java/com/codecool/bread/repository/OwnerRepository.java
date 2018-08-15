package com.codecool.bread.repository;

import com.codecool.bread.model.Owner;
import com.codecool.bread.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    Optional<Owner> findByUserId(Integer userId);

    Optional<Owner> findByEmail(String email);

    @Query(value= "select owner.id AS id, user_id,first_name,\n" +
            "last_name, owner.address_id As address_id,\n" +
            "owner.email as email, owner.phone as phone,\n" +
            "owner.enabled AS enabled from owner\n" +
            "left join restaurant\n" +
            "on owner.id = restaurant.owner_id\n" +
            "where restaurant.id = ?1", nativeQuery= true)
    Optional<Owner> findByRestaurantId(Integer restaurantId);
}
