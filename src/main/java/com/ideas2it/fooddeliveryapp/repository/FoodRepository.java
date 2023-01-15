package com.ideas2it.fooddeliveryapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ideas2it.fooddeliveryapp.model.Food;

/**
 * Interface in which extends JpaRepository so that inbuilt methods can be used
 * in employeeServiceImpl
 *
 * @author M Mohamed Riyas
 *
 * @version 1.0
 */
public interface FoodRepository extends JpaRepository<Food, Integer> {

    @Query("SELECT f from Food f where f.isDeleted = false")
    List<Food> findAll();

    @Modifying
    @Query(value = "UPDATE Food f SET f.isDeleted = true WHERE f.id = :id")
    void deleteById(int id);
}
