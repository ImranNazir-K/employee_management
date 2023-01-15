package com.ideas2it.fooddeliveryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideas2it.fooddeliveryapp.model.Delivery;


public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
}
