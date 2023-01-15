package com.ideas2it.fooddeliveryapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ideas2it.fooddeliveryapp.constant.FoodConstant;
import com.ideas2it.fooddeliveryapp.dto.FoodDTO;
import com.ideas2it.fooddeliveryapp.exception.NotFoundException;
import com.ideas2it.fooddeliveryapp.helper.FoodHelper;
import com.ideas2it.fooddeliveryapp.model.Food;
import com.ideas2it.fooddeliveryapp.repository.FoodRepository;
import com.ideas2it.fooddeliveryapp.service.FoodService;

/**
 * Implementation of FoodService class for Food entity
 *
 * @author M Mohamed Riyas
 *
 * @version 1.0
 */
@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodDTO createFood(FoodDTO foodDto) {
        if (foodDto.getIsAvailable() == null) {
            foodDto.setIsAvailable(Boolean.TRUE);
        }
        Food food = FoodHelper.toFood(foodDto);
        foodRepository.save(food);
        return FoodHelper.toFoodDto(food);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FoodDTO> getFoods() {
        List<Food> foodList = foodRepository.findAll();
        return FoodHelper.toFoodDtos(foodList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodDTO getFoodById(int id) {
        Food food = foodRepository.findById(id)
                .filter(foodObject -> foodObject.getIsDeleted() == false)
                .orElseThrow(() -> new NotFoundException(FoodConstant
                .FOOD_NOT_FOUND));
        return FoodHelper.toFoodDto(food);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FoodDTO updateFood(FoodDTO foodDto) {
        if (foodDto.getIsAvailable() == null) {
            foodDto.setIsAvailable(Boolean.TRUE);
        }
        Food food = FoodHelper.toFood(foodDto);
        foodRepository.save(food);
        return FoodHelper.toFoodDto(food);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteFood(int id) {
        if (foodRepository.existsById(id)) {
            foodRepository.deleteById(id);
            return true;
        } else {
            throw new NotFoundException(FoodConstant.FOOD_NOT_FOUND);
        }
    }
}
