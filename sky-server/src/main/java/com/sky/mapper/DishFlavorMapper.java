package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * Insert Flavor Data In Batches
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);
}
