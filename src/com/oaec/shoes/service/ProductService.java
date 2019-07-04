package com.oaec.shoes.service;

import com.oaec.shoes.entil.Product;

import java.util.List;

public interface ProductService {
    List<Product> query(String name,String sortId,String brandId,String page);
    Product query(String productId);
}
