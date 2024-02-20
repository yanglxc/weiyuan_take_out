package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.ArrayList;
import java.util.List;

public interface DishService {

    /**
     * Insert Dish With Flavor
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);

    /**
     * Dish_Paginated_Query
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * Dished_Deleted_In_Batches
     * @param ids
     * @return
     */
    void deleteBatch(List<Long> ids);

    /**
     * Query_Dish_By_Id
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * Update_DIsh_With_Flavor
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * Update_Dish_Status
     *
     * @param status
     * @param id
     * @return
     */
    void updateStatus(Integer status, Long id);

    /**
     * Get_By_Category_Id
     * @param categoryId
     * @return
     */
    ArrayList<DishVO> getByCategoryId(Long categoryId);
}
