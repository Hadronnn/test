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
import java.util.List;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String sortId = req.getParameter("sortId");
        String brandId = req.getParameter("brandId");
        String page = req.getParameter("page");
        if (page == null){
            page = "1";
        }
        List<Product> query = productService.query(name,sortId,brandId,page);
        req.setAttribute("list",query);
        req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req,resp);
    }
}
