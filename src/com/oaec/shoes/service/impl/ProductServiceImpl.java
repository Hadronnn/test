package com.oaec.shoes.service.impl;

import com.oaec.shoes.dao.ProductDao;
import com.oaec.shoes.dao.impl.ProductDaoImpl;
import com.oaec.shoes.entil.Product;
import com.oaec.shoes.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();
    @Override
    public List<Product> query(String name,String sortId,String brandId,String page) {
        if(sortId != null){
            if (brandId == null){
                return productDao.queryById(Integer.parseInt(sortId));
            }else{
                return productDao.queryByIdAndBrandId(Integer.parseInt(sortId),Integer.parseInt(brandId));
            }
        }
        if (sortId == null && brandId != null){
            return productDao.queryByBrandId(Integer.parseInt(brandId));
        }
        if (name == null) {
            return productDao.queryAll(Integer.parseInt(page));
        }else{
            return productDao.queryLike(name);
        }
    }

    @Override
    public Product query(String productId) {
        if (productId != null){
            return productDao.queryByProductId(Integer.parseInt(productId));
        }
        return null;
    }
}
