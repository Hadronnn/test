package com.oaec.shoes.servlet;

import com.oaec.shoes.entil.Product;
import com.oaec.shoes.service.ProductService;
import com.oaec.shoes.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        Product product = productService.query(productId);
        req.setAttribute("product",product);
        req.getRequestDispatcher("/WEB-INF/views/product.jsp").forward(req,resp);
    }
}
