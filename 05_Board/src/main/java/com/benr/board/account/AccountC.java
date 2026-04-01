package com.benr.board.account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AccountC", value = "/user-login")
public class AccountC extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 로그아웃하는일
        AccountDAO.ADAO.logout(request);

        // 어디로
        response.sendRedirect("hello-servlet");


//        request.setAttribute("content", "jsp/account/account.jsp");
//        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 로그인 하는일

        AccountDAO.ADAO.login(req);

        // 어디로
        AccountDAO.ADAO.loginCheck(req);
        req.setAttribute("content", "home.jsp");
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    public void destroy() {
    }
}