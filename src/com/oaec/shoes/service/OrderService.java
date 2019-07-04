package com.oaec.shoes.service;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Map<String,Object>> getOrders(Integer userId);

    Boolean sumit(Integer userId,Integer addressId,String[] productIds);
}
