package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController("adminShopController")
@RequestMapping("admin/shop")
@Slf4j
@Api(tags = "ShopController")
public class ShopController {

    @Autowired
    private ShopService shopService;

    /**
     * Set_Shop_Status
     * @param status
     * @return
     */
    @PutMapping("{status}")
    @ApiOperation("Set_Shop_Status")
    public Result setShopStatus(@PathVariable Integer status){
        log.info("Set_Shop_Status: {}", status);
        shopService.setShopStatus(status);
        return Result.success();
    }

    /**
     * Set_Shop_Status
     * @return
     */
    @GetMapping("status")
    @ApiOperation("Set_Shop_Status")
    public Result<Integer> getShopStatus(){
        Integer status = shopService.getShopStatus();
        log.info("Get_Shop_Status: {}", status);
        return Result.success(status);
    }
}
