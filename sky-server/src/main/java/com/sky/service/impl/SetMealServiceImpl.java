package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.exception.SetmealEnableFailedException;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealDishMapper;
import com.sky.mapper.SetMealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetMealService;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    private SetMealMapper setmealMapper;
    @Autowired
    private SetMealDishMapper setMealDishMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishMapper dishMapper;
    /**
     * Add_Meal
     * @return
     */
    @Override
    public void addMeal(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        List<SetmealDish> setMealDishes = new ArrayList<>();
        setMealDishes = setmealDTO.getSetmealDishes();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.insertMeal(setmeal);
        Long setMealId = setmeal.getId();
        for (SetmealDish setMealDish : setMealDishes) {
            setMealDish.setSetmealId(setMealId);
        }
        setMealDishMapper.insertBatch(setMealDishes);
    }

    /**
     * Dish_Paginated_Query
     * @param setmealPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
        Page<SetmealVO> page = setmealMapper.pageQuery(setmealPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * Delete_Meal_In_Batch
     * @param ids
     */
    @Override
    public void deleteInBatch(List<Long> ids) {
        List<Setmeal> setMeals = setmealMapper.getByIds(ids);
        for (Setmeal setmeal : setMeals) {
            if (setmeal.getStatus().equals(StatusConstant.ENABLE)){
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        }
        setmealMapper.deleteInBatch(ids);
        setMealDishMapper.deleteByMealIds(ids);
    }

    /**
     * Get_Meal_By_Id
     * @param id
     * @return
     */
    @Override
    public SetmealVO getById(Long id) {
        SetmealVO setmealVO = new SetmealVO();
        Setmeal setmeal = setmealMapper.getById(id);
        List<SetmealDish> setMealDishes = setMealDishMapper.getSetMealDishByMealId(id);
        BeanUtils.copyProperties(setmeal, setmealVO);
        setmealVO.setSetmealDishes(setMealDishes);
        setmealVO.setCategoryName(categoryMapper.getById(setmealVO.getCategoryId()));
        return setmealVO;
    }

    /**
     * Update_Meal
     * @param setmealDTO
     */
    @Transactional
    @Override
    public void update(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        Long setMealId = setmeal.getId();
        setmealMapper.update(setmeal);
        List<SetmealDish> setMealDishes = setmealDTO.getSetmealDishes();
        for (SetmealDish setMealDish : setMealDishes) {
           setMealDish.setSetmealId(setMealId);
        }
        setMealDishMapper.deleteByMealId(setMealId);
        setMealDishMapper.insertBatch(setMealDishes);
    }

    /**
     * Set_Status
     * @param status
     * @param id
     */
    @Override
    public void setStatus(Integer status, Long id) {
        List<SetmealDish> mealDish = setMealDishMapper.getSetMealDishByMealId(id);
        List<Long> dishIds = new ArrayList<>();
        for (SetmealDish dish : mealDish) {
            dishIds.add(dish.getDishId());
        }
        List<Dish> dishes = dishMapper.getByIds(dishIds);
        if (dishes != null && !dishes.isEmpty()){
            for (Dish dish : dishes) {
                if (dish.getStatus().equals(StatusConstant.DISABLE)){
                    throw new SetmealEnableFailedException(MessageConstant.SETMEAL_ENABLE_FAILED);
                }
            }
        }
        setmealMapper.setMealStatus(status, id);
    }

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    public List<Setmeal> list(Setmeal setmeal) {
        List<Setmeal> list = setmealMapper.list(setmeal);
        return list;
    }

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    public List<DishItemVO> getDishItemById(Long id) {
        return setmealMapper.getDishItemBySetmealId(id);
    }
}
