package com.benr.board.jquery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HumanC", value = "/get-data")
public class HumanC extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

//        HumanDAO.test1(request);
//        HumanDAO.test2(request, response);
//        HumanDAO.test3(request, response);
//        HumanDAO.test4(request,response);
        HumanDAO.test5(request,response);
    }

    public void destroy() {
    }
}