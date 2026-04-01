package com.benr.board.account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AccountEditC", value = "/user-edit")
public class AccountEditC extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 수정하는 곳으로 보내기
        // 어디로
        AccountDAO.ADAO.loginCheck(request);
        request.setAttribute("content", "jsp/account/edit.jsp");
        request.getRequestDispatcher("index.jsp").forward(request, response);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (AccountDAO.ADAO.loginCheck(req)) {
            AccountDAO.ADAO.editUser(req);

        }
        resp.sendRedirect("user-info");

    }

    public void destroy() {
    }
}