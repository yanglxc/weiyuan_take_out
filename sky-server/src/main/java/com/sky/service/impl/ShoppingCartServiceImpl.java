package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetMealMapper setMealMapper;

    /**
     * Add_Cart
     * @param shoppingCartDTO
     */
    @Override
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        //determine if the items currently added to cart already exist
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);

        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);

        //if already exist, the item quantity add one
        if (list != null && !list.isEmpty()){
            ShoppingCart cart = list.get(0);
            cart.setNumber(cart.getNumber() + 1);
            shoppingCartMapper.updateNumberById(cart);
        } else {
            //if not exist, add a cart data
            Long dishId = shoppingCartDTO.getDishId();
            if (dishId != null){
                Dish dish = dishMapper.getById(dishId);

                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());
            } else {
                Setmeal setMeal = setMealMapper.getById(shoppingCartDTO.getSetmealId());

                shoppingCart.setName(setMeal.getName());
                shoppingCart.setImage(setMeal.getImage());
                shoppingCart.setAmount(setMeal.getPrice());
            }

            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());

            shoppingCartMapper.inset(shoppingCart);
        }

    }

    /**
     * Query_Cart
     * @return
     */
    @Override
    public List<ShoppingCart> showShoppingCart() {
        Long userId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart = ShoppingCart.builder()
                        .userId(userId)
                        .build();
        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);
        return list;
    }

    /**
     * Clean_Cart
     */
    @Override
    public void cleanCart() {
        Long userId = BaseContext.getCurrentId();
        shoppingCartMapper.cleanAllCartDataByUserId(userId);
    }

    /**
     * Delete_Cart
     * @param shoppingCartDTO
     */
    @Override
    public void deleteCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        ShoppingCart shoppingCart2 = shoppingCartMapper.list(shoppingCart).get(0);
        Integer number = shoppingCart2.getNumber();
        if (number == 1){
            shoppingCartMapper.deleteCart(shoppingCartDTO);
        } else if (number > 1){
            shoppingCart2.setNumber(shoppingCart2.getNumber() - 1);
            shoppingCartMapper.updateNumberById(shoppingCart2);
        }
    }
}
