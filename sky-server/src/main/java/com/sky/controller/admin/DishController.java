package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
