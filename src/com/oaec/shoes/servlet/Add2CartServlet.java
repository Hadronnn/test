package com.oaec.shoes.servlet;

import com.alibaba.fastjson.JSON;
import com.oaec.shoes.entil.User;
import com.oaec.shoes.service.CartService;
import com.oaec.shoes.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/add2cart")
public class Add2CartServlet extends HttpServlet {
    private CartService cartService = new CartServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        int productId = Integer.parseInt(req.getParameter("productId"));
        int num = Integer.parseInt(req.getParameter("num"));
        Map<String, Object> map = cartService.addCart(userId, productId, num);
        Map<String,Object> json = new HashMap<>();
        if (map.containsKey("result")){
            json.put("result",map.get("result"));
        }else{
            json.put("error",map.get("error"));
        }
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print(JSON.toJSONString(json));
        writer.close();
    }
}
