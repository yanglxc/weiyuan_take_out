package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Dish Management
 */

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "DishController")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;
    @ApiOperation(value = "Add_Dish")
    @PostMapping
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("Adding Dish: {}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * Dish_Paginated_Query
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "Dish_Paginated_Query")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("Dish_Paginated_Query:{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Dished_Deleted_In_Batches
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("Dished_Deleted_In_Batches")
    public Result delete(@RequestParam List<Long> ids){
        log.info("Dished_Deleted_In_Batches: {}", ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }
}
