package com.oaec.shoes.service.impl;

import com.oaec.shoes.dao.CartDao;
import com.oaec.shoes.dao.impl.CartDaoImpl;
import com.oaec.shoes.entil.Cart;
import com.oaec.shoes.service.CartService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartServiceImpl implements CartService {
    private CartDao cartDao = new CartDaoImpl();
    @Override
    public Map<String, Object> addCart(Integer userId, Integer productId, Integer num) {
        Map<String, Object> map = new HashMap<>();
        Boolean aBoolean = false;
        if (cartDao.queryByUserIdANDProductId(userId,productId) != null){
            aBoolean = cartDao.doUpdate(userId,productId,num);
        }else{
            Cart cart = new Cart(userId,productId,num);
            aBoolean = cartDao.doInsert(cart);
        }
        if (aBoolean){
            map.put("result","添加成功");
        }else{
            map.put("error","添加失败");
        }
        return map;
    }
    @Override
    public Map<String, Object> cartInfo(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = cartDao.queryByUserId(userId);
        map.put("list",list);
        return map;
    }

    @Override
    public Map<String, Object> deleteCart(Integer userId, Integer productId) {
        Map<String, Object> map = new HashMap<>();
        int aBoolean = cartDao.doDelete(userId, productId);
        if (aBoolean == 1){
            map.put("result","删除成功");
        }else{
            map.put("error","删除失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> updateNum(Integer userId, Integer productId, String action) {
        Map<String,Object> map = new HashMap<>();
        if (action.equalsIgnoreCase("add")){
            int i = cartDao.doUpdateAdd(userId, productId);
            map.put("add",i);
        }else{
            int i = cartDao.doUpdateSub(userId, productId);
            map.put("sub",i);
        }
        return map;
    }

    @Override
    public Boolean queryCartNum(Integer userId, Integer productId) {
        Cart cart = cartDao.queryByUserIdANDProductIdAndNum(userId, productId);
        return cart == null;
    }

    @Override
    public List<Map<String, Object>> queryProduct4Order(Integer userId, String[] productIds) {
        return cartDao.queryProduct4Order(userId,productIds);
    }

    @Override
    public Map<String, Object> total(Integer userId, String[] productIds) {
        return cartDao.total(userId, productIds);
    }
}
