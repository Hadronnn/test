package com.oaec.shoes.servlet;

import com.oaec.shoes.entil.Address;
import com.oaec.shoes.entil.User;
import com.oaec.shoes.service.AddressService;
import com.oaec.shoes.service.CartService;
import com.oaec.shoes.service.impl.AddressServiceImpl;
import com.oaec.shoes.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private AddressService addressService = new AddressServiceImpl();
    private CartService cartService = new CartServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String[] productIds = req.getParameterValues("productId");
        List<Address> list = addressService.queryByUserId(user.getUserId());
        List<Map<String, Object>> maps = cartService.queryProduct4Order(user.getUserId(), productIds);
        Map<String, Object> total = cartService.total(user.getUserId(), productIds);
        req.setAttribute("product",maps);
        req.setAttribute("list",list);
        req.setAttribute("total",total);
        req.getRequestDispatcher("/WEB-INF/views/order.jsp").forward(req,resp);
    }
}
