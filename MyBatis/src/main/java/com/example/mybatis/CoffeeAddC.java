package com.example.mybatis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CoffeeAddC", value = "/add-coffee")
public class CoffeeAddC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        CoffeeDAO.addCoffee(request);
       response.sendRedirect("hello-servlet");

    }

    public void destroy() {
    }
}