package com.oaec.shoes.service;

import java.util.List;
import java.util.Map;

public interface CartService {
    /*
    * 加入购物车
    * */
    Map<String,Object> addCart(Integer userId,Integer productId,Integer num);

    Map<String,Object> cartInfo(Integer userId);

    Map<String,Object> deleteCart(Integer userId,Integer productId);

    Map<String,Object> updateNum(Integer userId,Integer productId,String action);
    Boolean queryCartNum(Integer userId,Integer productId);
    List<Map<String, Object>> queryProduct4Order(Integer userId, String[] productIds);
    Map<String, Object> total(Integer userId, String[] productIds);
}
