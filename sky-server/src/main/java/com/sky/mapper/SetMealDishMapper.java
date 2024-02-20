package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealDishMapper {
    /**
     * Get_SetMeal_Ids_By_DishIds
     * @param dishIds
     * @return
     */
    List<Long> getSetMealIdsByDishIds(List<Long> dishIds);

    /**
     * Add_SetMealDish_In_Batch
     * @param setMealDishes
     */
    void insertBatch(List<SetmealDish> setMealDishes);

    /**
     * Get_SetMealDish_By_MealId
     * @param mealId
     * @return
     */
    List<SetmealDish> getSetMealDishByMealId(Long mealId);

    /**
     * Delete_By_MealId
     * @param mealId
     */
    @Delete("delete from setmeal_dish where setmeal_id = #{mealId}")
    void deleteByMealId(Long mealId);

    void deleteByMealIds(List<Long> mealIds);
}
