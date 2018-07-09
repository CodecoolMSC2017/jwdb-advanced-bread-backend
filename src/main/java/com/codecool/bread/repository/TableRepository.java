package com.codecool.bread.repository;

import com.codecool.bread.model.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends CrudRepository<Table, Integer> {
}
