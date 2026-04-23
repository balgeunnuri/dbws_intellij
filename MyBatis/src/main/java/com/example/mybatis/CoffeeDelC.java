package com.example.mybatis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CoffeeDelC", value = "/del-coffee")
public class CoffeeDelC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        CoffeeDAO.delCoffee(request); // 파라미터 넘겨 받아야하니까 request
       response.sendRedirect("hello-servlet"); // 전체 조회 폼

    }

    public void destroy() {
    }
}