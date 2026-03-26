package com.benr.board.movie;

import com.benr.board.account.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MovieC", value = "/movie")
public class MovieC extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (request.getParameter("type") != null && request.getParameter("type").equals("d")) {
//            MovieDAO.deleteMovie(request);
            MovieDAO.MDAO.deleteMovie(request);
            System.out.println("del");
            AccountDAO.ADAO.loginCheck(request);
            response.sendRedirect("movie");

        } else {
            String p =  request.getParameter("p");
            int page = 1;
            if (p != null) {
                page = Integer.parseInt(p);
            }

//            MovieDAO.selectAllMovie(request);
//            MovieDAO.paging(page, request);
            MovieDAO.MDAO.selectAllMovie(request);
            MovieDAO.MDAO.paging(page, request);

            AccountDAO.ADAO.loginCheck(request);
            request.setAttribute("content", "jsp/movie/movie.jsp");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 등록하는 일
//        MovieDAO.addMovie(req);
            MovieDAO.MDAO.addMovie(req);

//        req.setAttribute("content", "jsp/movie/movie.jsp");
//        req.getRequestDispatcher("index.jsp").forward(req,resp);

        AccountDAO.ADAO.loginCheck(req);
        resp.sendRedirect("movie");

        // 어디로?

    }

    public void destroy() {
    }
}