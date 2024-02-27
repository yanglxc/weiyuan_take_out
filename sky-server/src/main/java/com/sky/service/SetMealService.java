package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetMealService {
    /**
     * Add_Meal
     * @return
     */
    void addMeal(SetmealDTO setmealDTO);

    /**
     * Dish_Paginated_Query
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * Delete_Meal_In_Batch
     * @param ids
     */
    void deleteInBatch(List<Long> ids);

    /**
     * Get_Meal_By_Id
     * @param id
     * @return
     */
    SetmealVO getById(Long id);

    /**
     * Update_Meal
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    /**
     * Set_Status
     * @param status
     * @param id
     */
    void setStatus(Integer status, Long id);

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);
}
