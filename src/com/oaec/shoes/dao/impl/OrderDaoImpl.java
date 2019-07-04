package com.oaec.shoes.dao.impl;

import com.oaec.shoes.dao.OrderDao;
import com.oaec.shoes.entil.Order;
import com.oaec.shoes.entil.Product;
import com.oaec.shoes.util.CommonDao;

import java.util.List;
import java.util.Map;

public class OrderDaoImpl extends CommonDao implements OrderDao {
    @Override
    public int doInsert(Integer userId, Integer addressId) {
        int orderId = getOrderId();
        String sql = "INSERT INTO orders VALUES(?,?,?,sysdate)";
        int i = executeUpdate(sql,orderId, userId, addressId);
        if (i == 1){
            return orderId;
        }
        return 0;
    }

    @Override
    public int doInsertInfo(Map<String, Object> param) {
        String sql = "INSERT INTO orderinfo VALUES(seq_orderinfo.nextval,?,?,?,?,?,?)";
        return executeUpdate(sql,param.get("ORDER_ID"),param.get("PRODUCT_ID"),param.get("PRODUCT_NAME"),param.get("PIC_ADRESS"),param.get("PRICE"),param.get("NUM"));
    }

    @Override
    public List<Map<String,Object>> queryByUserId(Integer userId) {
        String sql = "SELECT * FROM orders WHERE USER_ID = ? ORDER BY orders.ORDER_ID DESC";
        return query4MapList(sql,userId);
    }

    @Override
    public List<Map<String,Object>> queryProductByOrderId(Integer orderId) {
        String sql= "SELECT orderinfo.PRODUCT_ID,orderinfo.PIC_ADRESS,orderinfo.PRODUCT_NAME,orderinfo.NUM,orderinfo.PRICE FROM orders,orderinfo WHERE orders.ORDER_ID = orderinfo.ORDER_ID AND orders.ORDER_ID = ?";
        return query4MapList(sql,orderId);
    }

    @Override
    public Double getTotalPrice(Integer orderId) {
        String sql= "SELECT SUM(orderinfo.NUM * orderinfo.PRICE) TOTAL_PRICE FROM orders,orderinfo WHERE orders.ORDER_ID = orderinfo.ORDER_ID AND orders.ORDER_ID = ?";
        Map<String, Object> map = query4Map(sql, orderId);
        return Double.parseDouble(map.get("TOTAL_PRICE").toString());
    }

    private int getOrderId(){
        String sql = "SELECT seq_order.nextval order_id FROM dual";
        Map<String, Object> map = query4Map(sql);
        System.out.println(map);
        return Integer.parseInt(map.get("ORDER_ID").toString());
    }
}
