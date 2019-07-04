package com.oaec.shoes.service.impl;

import com.oaec.shoes.dao.AddressDao;
import com.oaec.shoes.dao.CartDao;
import com.oaec.shoes.dao.OrderDao;
import com.oaec.shoes.dao.ProductDao;
import com.oaec.shoes.dao.impl.AddressDaoImpl;
import com.oaec.shoes.dao.impl.CartDaoImpl;
import com.oaec.shoes.dao.impl.OrderDaoImpl;
import com.oaec.shoes.dao.impl.ProductDaoImpl;
import com.oaec.shoes.entil.Address;
import com.oaec.shoes.entil.Order;
import com.oaec.shoes.service.OrderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private CartDao cartDao = new CartDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();
    private AddressDao addressDao= new AddressDaoImpl();

    @Override
    public List<Map<String, Object>> getOrders(Integer userId) {
        List<Map<String,Object>> orderList = orderDao.queryByUserId(userId);
        for (Map<String,Object> order : orderList) {
            Address address = addressDao.queryByAddressId(Integer.parseInt(order.get("ADRESS_ID").toString()));
            order.put("address",address);
            Double totalPrice = orderDao.getTotalPrice(Integer.parseInt(order.get("ORDER_ID").toString()));
            order.put("totalPrice",totalPrice);
            List<Map<String, Object>> products = orderDao.queryProductByOrderId(Integer.parseInt(order.get("ORDER_ID").toString()));
            order.put("products",products);
            System.out.println(products);
        }
        return orderList;
    }

    @Override
    public Boolean sumit(Integer userId, Integer addressId, String[] productIds) {
        int result = 0;
        int orderId = orderDao.doInsert(userId, addressId);
        for (String productId : productIds) {
            Integer pid = Integer.parseInt(productId);
            Map<String, Object> map = cartDao.query(userId, pid);
            map.put("ORDER_ID",orderId);
            result += orderDao.doInsertInfo(map);
            result += cartDao.doDelete(userId,pid);
            result += productDao.updateStockAndSalesVolume(pid,Integer.parseInt(map.get("NUM").toString()));
        }
        return result>0;
    }
}
