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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/delete4cart")
public class Delete4CartServlet extends HttpServlet {
    private CartService cartService = new CartServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String productId = req.getParameter("productId");
        Map<String, Object> map = cartService.deleteCart(user.getUserId(), Integer.parseInt(productId));
        Map<String,Object> json = new HashMap<>();
        if (map.containsKey("result")){
            json.put("result",map.get("result"));
        }else{
            json.put("error",map.get("error"));
        }
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print(JSON.toJSONString(json));
        System.out.println(json);
        writer.close();
    }
}
