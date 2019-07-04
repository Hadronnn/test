package com.oaec.shoes.dao;

import com.oaec.shoes.entil.User;

public interface UserDao {
    /**
     * 根据用户Id 查找用户
     * @param userId
     * @return
     */
    User queryByUserId(Integer userId);

    /**
     * 根据用户名查用户
     * @param userName
     * @return
     */
    User queryByUserName(String userName);

    /**
     * 根据手机号查用户
     * @param phone
     * @return
     */
    User queryByPhone(String phone);

    User queryByUserNameAndPassword(String userName,String Password);
    /**
     * 添加用户
     * @param user
     * @return
     */
    Boolean doInsert(User user);
    User queryByUserIdsAndPassword(Integer userId,String Password);
    Boolean updatePassword(Integer userId,String password);
}
