package com.codecool.bread.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Table extends CrudRepository<Table, Integer> {
}
