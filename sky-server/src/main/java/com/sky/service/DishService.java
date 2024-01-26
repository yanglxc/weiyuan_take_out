package com.sky.service;

import com.sky.dto.DishDTO;

public interface DishService {

    /**
     * Insert Dish With Flavor
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);
}
