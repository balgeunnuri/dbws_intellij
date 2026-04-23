package com.example.mybatis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CoffeeEditC", value = "/update-coffee")
public class CoffeeEditC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        CoffeeDAO.editCoffee(request);
        request.getRequestDispatcher("result.jsp").forward(request, response);

    }

    public void destroy() {
    }
}