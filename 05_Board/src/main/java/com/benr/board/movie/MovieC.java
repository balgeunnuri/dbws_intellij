package com.benr.board.movie;

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
            MovieDAO.deleteMovie(request);
            System.out.println("del");
            response.sendRedirect("movie");

        } else {
            MovieDAO.selectAllMovie(request);
            request.setAttribute("content", "jsp/movie/movie.jsp");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 등록하는 일
        MovieDAO.addMovie(req);

//        req.setAttribute("content", "jsp/movie/movie.jsp");
//        req.getRequestDispatcher("index.jsp").forward(req,resp);
        resp.sendRedirect("movie");

        // 어디로?

    }

    public void destroy() {
    }
}