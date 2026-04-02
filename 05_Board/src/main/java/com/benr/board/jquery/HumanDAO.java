package com.benr.board.jquery;

import com.benr.board.main.DBManager;
import com.benr.board.main.DBManager_new;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HumanDAO {
    public static void test1(HttpServletRequest request) {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println(name);
        System.out.println(age);
    }

    public static void test2(HttpServletRequest request, HttpServletResponse response) {

        String str = "하잉";
        // json 자바스크립트옵젝 -> json-simple, gson
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("string", str);

        response.setContentType("application/json;charset=utf-8");
        try {
            response.getWriter().println(jsonObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void test3(HttpServletRequest request, HttpServletResponse response) {

        Human human = new Human();
        human.setName("nr");
        human.setAge(21);

        JsonObject jobj = new JsonObject();
//        jobj.addProperty("name", human.getName());
//        jobj.addProperty("age", human.getAge());

        response.setContentType("application/json; charset=utf-8");

        try {
            // 1. 객체 응답.
//            System.out.println(human.toJSON());
//            response.getWriter().println(human.toJSON());

            // 2. 객체 키값 실어서 전송
            JsonObject obj = new JsonObject();
            JsonParser parser = new JsonParser();
            obj.add("person", parser.parse(human.toJSON()));
            response.getWriter().println(obj);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void test4(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json; charset=utf-8");

        Human human1 = new Human(1,"mz1",10);
        Human human2 = new Human(2,"mz2",10);
        Human human3 = new Human(3,"mz3",10);
        System.out.println("-----------------");
        System.out.println(human1);
        System.out.println(human1.toString());

        ArrayList<String> humans = new ArrayList<>();
        humans.add(human1.toJSON());
        humans.add(human2.toJSON());
        humans.add(human3.toJSON());
        System.out.println(humans);
        try {
            // 1. 배열 응답
//            response.getWriter().println(humans);

            // 2. 키 값 부여
            JsonObject obj = new JsonObject();
            JsonParser parser = new JsonParser();
            obj.add("people", parser.parse(humans.toString()));
            response.getWriter().println(obj);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void test5(HttpServletRequest request, HttpServletResponse response) {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBManager_new.connect();
            pstmt =con.prepareStatement("select * from human_test");
            rs = pstmt.executeQuery();

            Human human = new Human(); // 오버로딩은 순서중요
            ArrayList<String> humans = new ArrayList<>();
            while (rs.next()){

                human.setAge(rs.getInt("h_age"));
                human.setNo(rs.getInt("h_no"));
                human.setName(rs.getString("h_name"));
                humans.add(human.toJSON());

            }
            System.out.println(humans);
            response.setContentType("application/json; charset=utf-8");
//            response.getWriter().println(humans); // 왜 주석처리 안 하면 페이지에 제이쿼리문 안 뜸?

            JsonObject obj = new JsonObject();
            JsonParser parser = new JsonParser();
            obj.add("people", parser.parse(humans.toString()));
            response.getWriter().println(obj);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }
}
