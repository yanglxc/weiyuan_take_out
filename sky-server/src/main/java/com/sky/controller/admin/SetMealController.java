package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetMealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Api(tags = "MealController")
@Slf4j
public class SetMealController {
    @Autowired
    private SetMealService setMealService;
    /**
     * Add_Meal
     * @return
     */
    @PostMapping
    @ApiOperation("Add_Meal")
    public Result addMeal(@RequestBody SetmealDTO setmealDTO){
        log.info("Add_Meal: {}", setmealDTO);
        setMealService.addMeal(setmealDTO);
        return Result.success();
    }

    /**
     * Meal_Paginated_Query
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("Meal_Paginated_Query")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("Dish_Paginated_Query: {}", setmealPageQueryDTO);
        PageResult pageResult = setMealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Delete_Meal_In_Batch
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("Delete_Meal_In_Batch")
    public Result delete(@RequestParam List<Long> ids){
        log.info("Delete_Meal_In_Batch: {}", ids);
        setMealService.deleteInBatch(ids);

        return Result.success();
    }

    /**
     * Update_Meal
     * @param setmealDTO
     * @return
     */
    @PutMapping
    @ApiOperation("Update_Meal")
    public Result update(@RequestBody SetmealDTO setmealDTO){
        log.info("Update_Meal: {}", setmealDTO);
        setMealService.update(setmealDTO);
        return Result.success();
    }

    /**
     * Get_Meal_By_Id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("Get_Meal_By_Id")
    public Result<SetmealVO> getById(@PathVariable Long id){
        log.info("Get_Meal_By_Id: {}", id);
        SetmealVO setmealVO = setMealService.getById(id);
        return Result.success(setmealVO);
    }

    /**
     * Set_Status
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Set_Status")
    public Result setStatus(@PathVariable Integer status, @RequestParam Long id){
        log.info("Set_Status: {} {}", status, id);
        setMealService.setStatus(status, id);
        return Result.success();
    }
}
