package com.oaec.shoes.servlet;

import com.oaec.shoes.entil.User;
import com.oaec.shoes.service.CartService;
import com.oaec.shoes.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private CartService cartService = new CartServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Integer userId = user.getUserId();
        Map<String, Object> map = cartService.cartInfo(userId);
        if (map.containsKey("list")){
            req.setAttribute("list",map.get("list"));
        }
        req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req,resp);
    }
}
