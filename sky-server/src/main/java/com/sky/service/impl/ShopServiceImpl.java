package com.sky.service.impl;

import com.sky.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Set_Shop_Status
     * @param status
     */
    @Override
    public void setShopStatus(Integer status) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("shopStatus", status);
    }

    /**
     * Set_Shop_Status
     * @return
     */
    @Override
    public Integer getShopStatus() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Integer status = (Integer) valueOperations.get("shopStatus");
        return status;
    }
}
