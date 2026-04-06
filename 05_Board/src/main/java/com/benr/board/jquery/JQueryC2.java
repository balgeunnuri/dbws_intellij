package com.benr.board.jquery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "JQueryC2", value = "/get-json")
public class JQueryC2 extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        System.out.println("json get 요청~~");
        response.setContentType("application/json; charset=utf-8"); // 이건 정확히 무슨 기능코드?
        HumanDAO.getJSON();
        response.getWriter().println(HumanDAO.getJSON());// 말 그대로 그리는 코드

        // 어디로? 갈지 고려하면 안 됨 여기서.
        // 비동기 요청이니까 이 화면에 머물러 있고 싶다는 것.
        // 데이터가 받아지기만 바라는 것.
    }

    public void destroy() {
    }
}