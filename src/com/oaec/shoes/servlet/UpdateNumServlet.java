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

@WebServlet("/updatenum")
public class UpdateNumServlet extends HttpServlet {
    CartService cartService = new CartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        User user = (User) req.getSession().getAttribute("user");
        String action = req.getParameter("action");
        Boolean ma = cartService.queryCartNum(user.getUserId(), Integer.parseInt(productId));
        Map<String,Object> json = new HashMap<>();
        if (ma){
            Map<String, Object> map = cartService.updateNum(user.getUserId(), Integer.parseInt(productId), action);
            if (map.containsKey("add")){
                if (map.get("add").equals(1)){
                    json.put("add",true);
                }else{
                    json.put("add",false);
                }
            }else{
                if (map.get("sub").equals(1)){
                    json.put("sub",true);
                }else{
                    json.put("sub",false);
                }
            }
        }
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print(JSON.toJSONString(json));
        System.out.println(json);
        writer.close();
    }
}
