package com.oaec.shoes.dao.impl;

import com.oaec.shoes.dao.ProductDao;
import com.oaec.shoes.entil.Product;
import com.oaec.shoes.util.CommonDao;

import java.util.List;
import java.util.Map;

public class ProductDaoImpl extends CommonDao implements ProductDao {
    @Override
    public List<Product> queryAll(Integer page) {
        String sql = "SELECT * FROM (SELECT ROWNUM rn,t1.* FROM(SELECT * FROM product WHERE state = 0 ORDER BY SALES_VOLUME DESC) t1)t2 WHERE t2.rn > ? AND t2.rn <= ?";
        List<Product> products = query4BeanList(sql, Product.class,(page -1)*pageSize,page*pageSize);
        return products;
    }

    @Override
    public int queryAllCount() {
        String sql = "SELECT COUNT(PRODUCT_ID) count FROM product";
        Map<String, Object> map = query4Map(sql);
        return Integer.parseInt(map.get("count").toString());
    }

    @Override
    public List<Product> queryLike(String name) {
        String sql = "SELECT * FROM product WHERE state = 0 AND UPPER(PRODUCT_NAME) LIKE UPPER(?) ORDER BY SALES_VOLUME DESC";
        return query4BeanList(sql,Product.class,"%"+name+"%");
    }

    @Override
    public List<Product> queryById(Integer sortId) {
        String sql = "SELECT * FROM product WHERE state = 0 AND SORT_ID = ? ORDER BY SALES_VOLUME DESC";
        return query4BeanList(sql,Product.class,sortId);
    }

    @Override
    public List<Product> queryByBrandId(Integer brandId) {
        String sql = "SELECT * FROM product WHERE state = 0 AND BRAND_ID = ? ORDER BY SALES_VOLUME DESC";
        return query4BeanList(sql,Product.class,brandId);
    }

    @Override
    public List<Product> queryByIdAndBrandId(Integer sortId, Integer brandId) {
        String sql = "SELECT * FROM product WHERE state = 0 AND SORT_ID = ? AND BRAND_ID = ? ORDER BY SALES_VOLUME DESC";
        return query4BeanList(sql,Product.class,sortId,brandId);
    }

    @Override
    public Product queryByProductId(Integer productId) {
        String sql = "SELECT * FROM product WHERE state = 0 AND PRODUCT_ID = ?";
        return query4Bean(sql,Product.class,productId);
    }

    @Override
    public int updateStockAndSalesVolume(Integer productId, Integer num) {
        String sql = "UPDATE product SET STOCK = STOCK - ?,SALES_VOLUME = SALES_VOLUME + ? WHERE PRODUCT_ID = ?";
        return executeUpdate(sql,num,num,productId);
    }

}
