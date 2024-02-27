package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j
@Api(tags = "ShoppingCartController")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * Add_Cart
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("Add_Cart")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("Add_Cart: {}",shoppingCartDTO);
        shoppingCartService.addShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    /**
     * Query_Cart
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("Query_Cart")
    public Result<List<ShoppingCart>> list(){
        List<ShoppingCart> list = shoppingCartService.showShoppingCart();
        return Result.success(list);
    }

    /**
     * Clean_Cart
     * @return
     */
    @DeleteMapping("/clean")
    @ApiOperation("Clean_Cart")
    public Result clean(){
        shoppingCartService.cleanCart();
        return Result.success();
    }

    /**
     * Delete_Cart
     * @return
     */
    @PostMapping("/sub")
    @ApiOperation("Delete_Cart")
    public Result delete(@RequestBody ShoppingCartDTO shoppingCartDTO){
        shoppingCartService.deleteCart(shoppingCartDTO);
        return Result.success();
    }
}
