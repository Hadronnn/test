package com.oaec.shoes.service.impl;

import com.oaec.shoes.dao.UserDao;
import com.oaec.shoes.dao.impl.UserDaoImpl;
import com.oaec.shoes.entil.User;
import com.oaec.shoes.service.UserService;
import com.oaec.shoes.util.CommonDao;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl extends CommonDao implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public Map<String, Object> login(String userName, String password) {
        HashMap<String, Object> map = new HashMap<>();
        User user = userDao.queryByUserNameAndPassword(userName,password);
        if (user == null){
            if (userDao.queryByUserName(userName) == null){
                map.put("error","用户名不存在");
            }else{
                map.put("error","密码错误");
            }
        }else{
            map.put("user",user);
            System.out.println(map);
        }
        return map;
    }

    @Override
    public Map<String, Object> register(String userName,String phone, String password) {
        Map<String,Object> map = new HashMap<>();
        User queryByUserName = userDao.queryByUserName(userName);
        if (queryByUserName != null){
            map.put("error","用户名已存在");
            return map;
        }
        User queryByPhone = userDao.queryByPhone(phone);
        if (queryByPhone != null){
            map.put("error","手机号已注册");
            return map;
        }
        Boolean aBoolean = userDao.doInsert(new User(userName, password, phone));
        if (aBoolean){
            User u = userDao.queryByUserName(userName);
            map.put("user",u);
            return map;
        }else {
            map.put("error","注册失败");
            return  map;
        }
    }

    @Override
    public Boolean editPassword(Integer userId, String password,String newpassword) {
        User user = userDao.queryByUserIdsAndPassword(userId, password);
        if (user == null){
            return false;
        }else{
            Boolean aBoolean = userDao.updatePassword(userId, newpassword);
            return aBoolean;
        }
    }
}
