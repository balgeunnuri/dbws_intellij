package com.benr.board.review;

import com.benr.board.account.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReviewDelC", value = "/review-del")
public class ReviewDelC extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 전체 조회하는 일
        ReviewDAO.RDAO.delReview(request);

        // 어디로?
        response.sendRedirect("review");
    }

    public void destroy() {
    }
}