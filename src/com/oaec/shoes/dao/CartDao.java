package com.oaec.shoes.dao;

import com.oaec.shoes.entil.Cart;

import java.util.List;
import java.util.Map;

public interface CartDao {
    /*
    * 添加购物车
    * */
    Boolean doInsert(Cart cart);
    List<Map<String ,Object>> queryByUserId(Integer userId);
    Cart queryByUserIdANDProductId(Integer userId,Integer productId);
    Boolean doUpdate(Integer userId,Integer productId,Integer num);
    int doDelete(Integer userId,Integer productId);
    int doUpdateAdd(Integer userId,Integer productId);
    int doUpdateSub(Integer userId,Integer productId);
    Cart queryByUserIdANDProductIdAndNum(Integer userId,Integer productId);
    List<Map<String ,Object>> queryProduct4Order(Integer userId,String[] productIds);
    Map<String,Object> total(Integer userId,String[] productIds);
    Map<String,Object> query(Integer userId,Integer productId);
}
