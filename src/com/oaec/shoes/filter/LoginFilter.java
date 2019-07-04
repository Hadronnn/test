package com.oaec.shoes.filter;

import com.alibaba.fastjson.JSON;
import com.oaec.shoes.util.HttpFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = {"/myxin","/myprod","/submit","/logout","/modifyAddress","/updatenum","/deleteaddress","/delete4cart","/add2adress","/remima","/cart","/add2cart","/address","/order","/myorder"})
public class LoginFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (request.getSession().getAttribute("user") == null){
            String requestURI = request.getRequestURI();
            String queryString = request.getQueryString();

            System.out.println(requestURI);
            request.getSession().setAttribute("uri",requestURI);
            if(requestURI.contains("add2cart")) {
                Map<String,Object> json = new HashMap<>();
                json.put("isLogin",false);
                response.setContentType("application/json;charset=utf-8");
                PrintWriter writer = response.getWriter();
//                    if (queryString != null){
//                        requestURI += "product?productId="+request.getParameter("productId");
//                    }
                requestURI = request.getContextPath()+"/product?productId="+request.getParameter("productId");
                json.put("uri",requestURI);
                writer.print(JSON.toJSONString(json));
                writer.close();
            }else{
                if (queryString != null){
                    requestURI =  requestURI + "?"+queryString;
                }
                response.sendRedirect(request.getContextPath()+"/login?uri="+requestURI);
            }
        }else{
            filterChain.doFilter(request,response);
        }
    }
}
