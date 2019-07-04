package com.oaec.shoes.servlet;

import com.alibaba.fastjson.JSON;
import com.oaec.shoes.entil.Address;
import com.oaec.shoes.entil.User;
import com.oaec.shoes.service.AddressService;
import com.oaec.shoes.service.impl.AddressServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/add2adress")
public class Add2AdressServlet extends HttpServlet {
    private AddressService addressService = new AddressServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String contact = req.getParameter("contact");
        String province = req.getParameter("province");
        String city = req.getParameter("city");
        String county = req.getParameter("county");
        String adress = req.getParameter("adress");
        String code = req.getParameter("code");
        User user = (User) req.getSession().getAttribute("user");
        Address address = new Address(province, city, Integer.parseInt(code), county, name, contact, adress, user.getUserId());
        Boolean aBoolean = addressService.addAddress(address);
        Map<String,Object> json = new HashMap<>();
        json.put("success",aBoolean);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print(JSON.toJSONString(json));
        writer.close();
    }
}
