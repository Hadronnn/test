package com.oaec.shoes.dao.impl;

import com.oaec.shoes.dao.UserDao;
import com.oaec.shoes.entil.User;
import com.oaec.shoes.util.CommonDao;

public class UserDaoImpl extends CommonDao implements UserDao {
    @Override
    public User queryByUserId(Integer userId) {
        String sql = "SELECT * FROM users WHERE USER_ID = ?";
        User user = query4Bean(sql, User.class, userId);
        return user;
    }

    @Override
    public User queryByUserName(String userName) {
        String sql = "SELECT * FROM users WHERE USER_NAME = ?";
        User user = query4Bean(sql, User.class, userName);
        return user;
    }

    @Override
    public User queryByPhone(String phone) {
        String sql = "SELECT * FROM users WHERE PHONE = ?";
        User user = query4Bean(sql, User.class, phone);
        return user;
    }

    @Override
    public User queryByUserNameAndPassword(String userName, String password) {
        String sql = "SELECT * FROM users WHERE USER_NAME = ? AND PASSWORD = ?";
        User user = query4Bean(sql, User.class,userName,password);
        return user;
    }

    @Override
    public Boolean doInsert(User user) {
        String sql = "INSERT INTO USERS VALUES(seq_users.nextval,?,?,0,?,0,sysdate)";
        int i = executeUpdate(sql, user.getUserName(), user.getPassword(), user.getPhone());
        return i == 1;
    }

    @Override
    public User queryByUserIdsAndPassword(Integer userId, String password) {
        String sql = "SELECT * FROM users WHERE USER_ID = ? AND PASSWORD = ?";
        return query4Bean(sql,User.class,userId,password);
    }

    @Override
    public Boolean updatePassword(Integer userId, String password) {
        String sql = "UPDATE users SET password = ? WHERE USER_ID = ?";
        return executeUpdate(sql,password,userId) == 1;
    }
}
