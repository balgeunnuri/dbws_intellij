package com.example.mybatis;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        CoffeeDAO.selectAllCoffee();
        List<CoffeeVO> coffees = CoffeeDAO.selectAllCoffee();
        System.out.println(coffees);
        request.setAttribute("coffees", coffees);
//        request.setAttribute("coffees", CoffeeDAO.selectAllCoffee());
        request.getRequestDispatcher("result.jsp").forward(request, response);

    }

    public void destroy() {
    }
}