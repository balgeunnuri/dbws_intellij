package com.benr.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MemberC", value = "/del")
public class MemberC extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       // 삭제하는일
        MemberDAO.delMember(request);
        // 어디로?
//        MemberDAO.getAllMember(request);
//        request.getRequestDispatcher("member_list.jsp").forward(request, response);
        response.sendRedirect("all");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public void destroy() {
    }
}