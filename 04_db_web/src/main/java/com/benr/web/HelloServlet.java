package com.benr.web;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/show")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       // 전체 조회하는 일
        ResM.showAllRes(request);

        // 어디로?
        request.getRequestDispatcher("index.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 등록하는일
        ResM.addRes(req);
        // 어디로
        resp.sendRedirect("show");
    }

    public void destroy() {
    }
}