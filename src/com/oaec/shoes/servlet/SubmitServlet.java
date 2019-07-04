package com.oaec.shoes.servlet;

import com.oaec.shoes.entil.User;
import com.oaec.shoes.service.OrderService;
import com.oaec.shoes.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/submit")
public class SubmitServlet extends HttpServlet {
    private OrderService orderService = new OrderServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Integer userId = user.getUserId();
        String addressId = req.getParameter("addressId");
        String[] productIds = req.getParameterValues("productId");
        Boolean sumit = orderService.sumit(userId, Integer.parseInt(addressId), productIds);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print("{\"success\":"+sumit+"}");
        writer.close();
    }
}
