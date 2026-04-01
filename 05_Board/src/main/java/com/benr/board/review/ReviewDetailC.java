package com.benr.board.review;

import com.benr.board.account.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReviewDetailC", value = "/review-detail")
public class ReviewDetailC extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 전체 조회하는 일
        ReviewDAO.RDAO.getReview(request);


        AccountDAO.ADAO.loginCheck(request);
        request.setAttribute("content", "jsp/review/review_detail.jsp");
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    public void destroy() {
    }
}