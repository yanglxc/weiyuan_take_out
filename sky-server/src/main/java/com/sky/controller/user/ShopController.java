package com.sky.controller.user;

import com.sky.result.Result;
import com.sky.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController("userShopController")
@RequestMapping("user/shop")
@Slf4j
@Api(tags = "ShopController")
public class ShopController {

    @Autowired
    private ShopService shopService;

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
