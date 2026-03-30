package com.benr.board.review;

import com.benr.board.account.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReviewUpdateC", value = "/review-update")
public class ReviewUpdateC extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 하나 조회하는 일
        ReviewDAO.RDAO.getReview(request);

        // 어디로?
        AccountDAO.ADAO.loginCheck(request);
        request.setAttribute("content", "jsp/review/review_update.jsp");
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 수정하는일
        ReviewDAO.RDAO.updateReview(req);

        // 어디로?
//        ReviewDAO.RDAO.getReview(req);
//        AccountDAO.ADAO.loginCheck(req);
//        req.setAttribute("content", "jsp/review/review_detail.jsp");
//        req.getRequestDispatcher("index.jsp").forward(req,resp);
        resp.sendRedirect("review-detail?pk="+req.getParameter("no"));
    }

    public void destroy() {
    }
}