package com.benr.board.account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AccountInfoC", value = "/user-info")
public class AccountInfoC extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // id로 조회하는 일 x - 이미 세션에 있으니까.

        if (AccountDAO.ADAO.loginCheck(request)) {
            request.setAttribute("content", "jsp/account/mypage.jsp");
        } else {
            request.setAttribute("content", "home.jsp");
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);


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