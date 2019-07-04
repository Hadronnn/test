package com.oaec.shoes.dao;

import com.oaec.shoes.entil.Order;
import com.oaec.shoes.entil.Product;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    int doInsert(Integer userId,Integer addressId);
    int doInsertInfo(Map<String,Object> param);
    List<Map<String,Object>> queryByUserId(Integer userId);
    List<Map<String,Object>> queryProductByOrderId(Integer orderId);

    Double getTotalPrice(Integer orderId);
}
