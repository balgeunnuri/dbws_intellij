package com.benr.board.movie;

import com.benr.board.main.DBManager;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MovieDAO {
    // 전체조회
    public static void selectAllMovie(HttpServletRequest req) {
    // 1. 값  or db
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from movie_test";

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            MovieDTO dto = null;
            ArrayList<MovieDTO> movies = new ArrayList<>();

            while (rs.next()) {
                dto = new MovieDTO();
                dto.setNo(rs.getInt("m_no"));
                dto.setTitle(rs.getString("m_title"));
                dto.setActor(rs.getString("m_actor"));
                dto.setImg(rs.getString("m_img"));
                dto.setStory(rs.getString("m_story"));
                movies.add(dto);
            }
            System.out.println(movies);
            req.setAttribute("movies", movies);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, pstmt, con);
        }


    }

    public static void addMovie(HttpServletRequest req) {
        // 1. 값 or db
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "insert into movie_test values(movie_test_seq.nextval,?,?,?,?)";
        String path = req.getServletContext().getRealPath("movieFile");
        System.out.println(path);

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);

            MultipartRequest mr = new MultipartRequest(req, path, 1024*1024*20, "utf-8", new DefaultFileRenamePolicy());

            String title = mr.getParameter("title");
            String actor = mr.getParameter("actor");
            String story = mr.getParameter("story");
            String fileName = mr.getFilesystemName("file");
            System.out.println(title);
            System.out.println(actor);
            System.out.println(story);
            System.out.println(fileName);

            story = story.replaceAll("\r\n", "<br>");
            // 한줄 띄어쓰기 자바에서 변경

            pstmt.setString(1, title);
            pstmt.setString(2, actor);
            pstmt.setString(3, fileName);
            pstmt.setString(4, story);

            if(pstmt.executeUpdate() == 1){
                System.out.println("add success");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(null, pstmt, con);
        }

    }


    public static void deleteMovie(HttpServletRequest request) {

        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "delete from movie_test where m_no = ?";


        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(request.getParameter("no")));

            if(pstmt.executeUpdate() == 1){
                System.out.println("del success");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(null, pstmt, con);
        }

    }

    public static void getMovie(HttpServletRequest request) {

        // 1. 값  or db
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from movie_test where m_no = ?";

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, request.getParameter("no"));

            rs = pstmt.executeQuery();
            MovieDTO dto = new MovieDTO();

           if (rs.next()) {
                dto.setNo(rs.getInt("m_no"));
                dto.setTitle(rs.getString("m_title"));
                dto.setActor(rs.getString("m_actor"));
                dto.setImg(rs.getString("m_img"));
                dto.setStory(rs.getString("m_story"));

            }
            System.out.println(dto);
            request.setAttribute("movie", dto);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, pstmt, con);
        }

    }
}
