package com.oaec.shoes.dao.impl;

import com.oaec.shoes.dao.CartDao;
import com.oaec.shoes.entil.Cart;
import com.oaec.shoes.entil.Product;
import com.oaec.shoes.util.CommonDao;

import java.util.List;
import java.util.Map;

public class CartDaoImpl extends CommonDao implements CartDao {
    @Override
    public Boolean doInsert(Cart cart) {
        String sql = "INSERT INTO cart VALUES(seq_cart.nextval,?,?,?)";
        int i = executeUpdate(sql, cart.getUserId(), cart.getProductId(), cart.getNum());
        return i == 1;
    }

    @Override
    public List<Map<String,Object>> queryByUserId(Integer userId) {
        String sql = "SELECT product.PRODUCT_ID,product.PIC_ADRESS,product.PRODUCT_NAME,brand.BRAND_NAME,product.PRICE,cart.NUM FROM cart,product,brand WHERE product.BRAND_ID = brand.BRAND_ID AND cart.product_id=product.product_id AND user_id = ?";
        List<Map<String, Object>> list = query4MapList(sql, userId);
        return list;
    }

    @Override
    public Cart queryByUserIdANDProductId(Integer userId, Integer productId) {
        String sql = "SELECT * FROM cart WHERE USER_ID = ? AND PRODUCT_ID = ?";
        return query4Bean(sql,Cart.class,userId,productId);
    }

    @Override
    public Boolean doUpdate(Integer userId, Integer productId , Integer num) {
        String sql = "UPDATE cart SET NUM = NUM + ? WHERE USER_ID = ? AND PRODUCT_ID = ?";
        return executeUpdate(sql,num,userId,productId) == 1;
    }

    @Override
    public int doDelete(Integer userId, Integer productId) {
        String sql = "DELETE FROM cart WHERE USER_ID = ? AND PRODUCT_ID = ?";
        return executeUpdate(sql,userId,productId);
    }

    @Override
    public int doUpdateAdd(Integer userId, Integer productId) {
        String sql = "UPDATE cart SET NUM = NUM + 1 WHERE USER_ID = ? AND PRODUCT_ID = ?";
        return executeUpdate(sql,userId,productId);
    }

    @Override
    public int doUpdateSub(Integer userId, Integer productId) {
        String sql = "UPDATE cart SET NUM = NUM - 1 WHERE USER_ID = ? AND PRODUCT_ID = ?";
        return executeUpdate(sql,userId,productId);
    }

    @Override
    public Cart queryByUserIdANDProductIdAndNum(Integer userId, Integer productId) {
        String sql = "SELECT * FROM cart WHERE USER_ID = ? AND PRODUCT_ID = ? AND NUM >= 5";
        return query4Bean(sql,Cart.class,userId,productId);
    }

    @Override
    public List<Map<String, Object>> queryProduct4Order(Integer userId, String[] productIds) {
        StringBuilder sql = new StringBuilder("SELECT product.PRODUCT_ID,product.PIC_ADRESS,product.PRODUCT_NAME,cart.NUM,product.PRICE FROM CART,PRODUCT WHERE cart.PRODUCT_ID = product.PRODUCT_ID AND cart.USER_ID = ? AND cart.PRODUCT_ID IN (");
        Object[] param = new Object[productIds.length+1];
        param[0] = userId;
        for (int i = 0; i < productIds.length ; i++) {
            sql.append("?,");
            param[i+1] = productIds[i];
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");
        return query4MapList(sql.toString(),param);
    }

    @Override
    public Map<String, Object> total(Integer userId, String[] productIds) {
        StringBuilder sql = new StringBuilder("SELECT SUM(NUM) NUM,SUM(NUM * PRICE) PRICE FROM CART,PRODUCT WHERE cart.PRODUCT_ID = product.PRODUCT_ID AND cart.USER_ID = ? AND cart.PRODUCT_ID IN (");
        Object[] param = new Object[productIds.length+1];
        param[0] = userId;
        for (int i = 0; i < productIds.length ; i++) {
            sql.append("?,");
            param[i+1] = productIds[i];
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");
        return query4Map(sql.toString(),param);
    }

    @Override
    public Map<String, Object> query(Integer userId, Integer productId) {
        String sql = "SELECT * FROM cart,product WHERE cart.PRODUCT_ID = product.PRODUCT_ID AND cart.USER_ID = ? AND cart.PRODUCT_ID = ?";
        return query4Map(sql,userId,productId);
    }
}
