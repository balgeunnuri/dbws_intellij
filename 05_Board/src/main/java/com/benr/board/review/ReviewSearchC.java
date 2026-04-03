package com.benr.board.review;

import com.benr.board.account.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReviewSearchC", value = "/review-search")
public class ReviewSearchC extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // 어디로? 신경 안 써도됨 비동기
        // 필요한 데이터가 뭐? => 검색한 결과 데이터 (json으로 받자)
        response.setContentType("application/json; charset=utf-8");
//        response.getWriter().println(ReviewDAO2.RDAO.searchReview(request));
        response.getWriter().println(ReviewDAO2.RDAO.showAllReview2(request));


    }

    public void destroy() {
    }
}