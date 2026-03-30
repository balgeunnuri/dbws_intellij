package com.benr.board.review;

import com.benr.board.account.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReviewAddC", value = "/review-add")
public class ReviewAddC extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 게시글 추가하러

        AccountDAO.ADAO.loginCheck(request);
        request.setAttribute("content", "jsp/review/review_add.jsp");
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 등록하는일
        ReviewDAO.RDAO.addReview(req);

        // 어디로?
        resp.sendRedirect("review");
    }

    public void destroy() {
    }
}