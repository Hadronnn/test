package com.oaec.shoes.servlet;

import com.alibaba.fastjson.JSON;
import com.oaec.shoes.entil.User;
import com.oaec.shoes.service.UserService;
import com.oaec.shoes.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/remima")
public class RemimaServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/remima.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String password = req.getParameter("password");
        String newpassword = req.getParameter("newpassword");
        Boolean a = userService.editPassword(user.getUserId(), password, newpassword);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print("{\"success\":"+a+"}");
        writer.close();
    }
}
