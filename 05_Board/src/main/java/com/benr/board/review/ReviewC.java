package com.benr.board.review;

import com.benr.board.account.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReviewC", value = "/review")
public class ReviewC extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 전체 조회하는 일
//        ReviewDAO.RDAO.showAllReview(request);

        String page = request.getParameter("p");
        int p = 1;
        if (page != null) {
            p = Integer.parseInt(page);
        }
        ReviewDAO.RDAO.paging(p, request);

        AccountDAO.ADAO.loginCheck(request);
        request.setAttribute("content", "jsp/review/review.jsp");
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    public void destroy() {
    }
}