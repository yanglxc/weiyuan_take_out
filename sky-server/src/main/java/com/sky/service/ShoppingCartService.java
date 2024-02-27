package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    /**
     * Add_Cart
     * @param shoppingCartDTO
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * Query_Cart
     * @return
     */
    List<ShoppingCart> showShoppingCart();

    /**
     * Clean_Cart
     */
    void cleanCart();

    /**
     * Delete_Cart
     * @param shoppingCartDTO
     */
    void deleteCart(ShoppingCartDTO shoppingCartDTO);
}
