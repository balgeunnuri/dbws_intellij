package com.benr.board.movie;

import com.benr.board.account.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class MovieUpdateC extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 그 영화 하나 정보 가져오는 일
//        MovieDAO.getMovie(request);
        MovieDAO.MDAO.getMovie(request);

        // 어디로?
        AccountDAO.ADAO.loginCheck(request);
        request.setAttribute("content", "jsp/movie/movie_edit.jsp");
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 수정하는 일
//        MovieDAO.upMovie2(req);
        MovieDAO.MDAO.upMovie2(req);

        // 어디로?
//        MovieDAO.getMovie(req);
//        req.setAttribute("content", "jsp/movie/movie_detail.jsp");
//        req.getRequestDispatcher("index.jsp").forward(req, resp);
        AccountDAO.ADAO.loginCheck(req);
        resp.sendRedirect("detail-movie?no=" + req.getAttribute("noo"));



    }

    public void destroy() {
    }
}