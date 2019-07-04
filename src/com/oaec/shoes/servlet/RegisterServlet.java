package com.oaec.shoes.servlet;

import com.alibaba.fastjson.JSON;
import com.oaec.shoes.service.UserService;
import com.oaec.shoes.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        Map<String, Object> json = new HashMap<>();
        Map<String, Object> map = userService.register(userName, phone, password);
        if (map.containsKey("user")){
            req.getSession().setAttribute("user",map.get("user"));
            json.put("result",true);
        }else{
            json.put("error",map.get("error"));
        }
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print(JSON.toJSONString(json));
        writer.close();
    }
}
