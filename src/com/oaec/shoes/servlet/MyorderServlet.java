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
import java.util.List;
import java.util.Map;

@WebServlet("/myorder")
public class MyorderServlet extends HttpServlet {
    private OrderService orderService = new OrderServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Integer userId = user.getUserId();
        List<Map<String, Object>> list = orderService.getOrders(userId);
        System.out.println(list);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/WEB-INF/views/myorderq.jsp").forward(req,resp);
    }
}
