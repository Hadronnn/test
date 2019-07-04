package com.oaec.shoes;

import com.alibaba.fastjson.JSON;
import com.oaec.shoes.dao.CartDao;
import com.oaec.shoes.dao.OrderDao;
import com.oaec.shoes.dao.ProductDao;
import com.oaec.shoes.dao.impl.CartDaoImpl;
import com.oaec.shoes.dao.impl.OrderDaoImpl;
import com.oaec.shoes.dao.impl.ProductDaoImpl;
import com.oaec.shoes.entil.Address;
import com.oaec.shoes.entil.Product;
import com.oaec.shoes.service.CartService;
import com.oaec.shoes.service.OrderService;
import com.oaec.shoes.service.impl.CartServiceImpl;
import com.oaec.shoes.service.impl.OrderServiceImpl;

import java.util.List;
import java.util.Map;

public class HelloWorld {
    public static void main(String[] args) {
        OrderDao orderDao = new OrderDaoImpl();
        List<Map<String, Object>> maps = orderDao.queryByUserId(1003);
        System.out.println(JSON.toJSONString(maps));
    }
}
