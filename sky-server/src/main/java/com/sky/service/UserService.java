package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

public interface UserService {

    /**
     * WeChat_Login
     * @param userLoginDTO
     * @return
     */
    User wechatLogin(UserLoginDTO userLoginDTO);
}
