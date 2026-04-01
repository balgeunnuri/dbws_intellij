package com.benr.board.account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AccountDelC", value = "/user-delete")
public class AccountDelC extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 어디로
       if (AccountDAO.ADAO.loginCheck(request)) {
           request.setAttribute("content", "jsp/account/edit.jsp");
       } else {
           request.setAttribute("content", "home.jsp");
       }
       request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (AccountDAO.ADAO.delUser(req)) {
            // 성공
            resp.sendRedirect("main");

        } else {
            // 아닐 땐
            resp.sendRedirect("user-info");
        }



    }

    public void destroy() {
    }
}