package com.oaec.shoes.dao;

import com.oaec.shoes.entil.Product;

import java.util.List;

public interface ProductDao {
    int pageSize = 4;
    List<Product> queryAll(Integer page);

    int queryAllCount();

    List<Product> queryLike(String name);

    List<Product> queryById(Integer sortId);

    List<Product> queryByBrandId(Integer brandId);

    List<Product> queryByIdAndBrandId(Integer sortId,Integer brandId);

    Product queryByProductId(Integer productId);

    int updateStockAndSalesVolume(Integer productId,Integer num);
}
