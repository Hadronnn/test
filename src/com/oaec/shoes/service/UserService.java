package com.oaec.shoes.service;

import com.oaec.shoes.entil.User;

import java.util.Map;

public interface UserService {
    /**
     * 登陆
     * @param userName
     * @param password
     * @return
     */
    Map<String,Object> login(String userName, String password);

    Map<String,Object> register(String userName,String phone,String password);

    Boolean editPassword(Integer userId,String password,String newpassword);
}
