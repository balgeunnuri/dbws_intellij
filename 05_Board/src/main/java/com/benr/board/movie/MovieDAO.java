package com.benr.board.movie;

import com.benr.board.main.DBManager;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MovieDAO {

    public static final MovieDAO MDAO = new MovieDAO();

    private MovieDAO() {

    }

    private ArrayList<MovieDTO> movies;

    // 전체조회
    public void selectAllMovie(HttpServletRequest req) {
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
            movies = new ArrayList<>();

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

    public void addMovie(HttpServletRequest req) {
        // 1. 값 or db
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "insert into movie_test values(movie_test_seq.nextval,?,?,?,?)";
//        String path = req.getServletContext().getRealPath("movieFile");
        String path = "C:\\nr\\dbws_intellij\\upload\\movieFile";
//        MAC 경로 다름 깃허브 충돌나면 위 부분을 체크해서 보면 됨.
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


    public void deleteMovie(HttpServletRequest request) {

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

    public void getMovie(HttpServletRequest request) {

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

    public void upMovie(HttpServletRequest req) {
        // 1. 값  or db
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "update movie_test set m_title=?, m_actor=?, m_story=? where m_no=?";


        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);

            String title = req.getParameter("title");
            String actor = req.getParameter("actor");
            String story = req.getParameter("story");
            String no = req.getParameter("no");

            story = story.replaceAll("\r\n", "<br>");
            System.out.println(title);
            System.out.println(actor);
            System.out.println(story);
            System.out.println(no);
            // 값 받기 끝나면 무조건 콘솔에 출혁하기

            pstmt.setString(1, title);
            pstmt.setString(2, actor);
            pstmt.setString(3, story);
            pstmt.setString(4, no);

            if(pstmt.executeUpdate() == 1){
                System.out.println("update success");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(null, pstmt, con);
        }

    }

    public void upMovie2(HttpServletRequest req) {
        // 1. 값 or db
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "update movie_test set m_title=?, m_actor=?, m_story=?, m_img=? where m_no=?";
//        String path = req.getServletContext().getRealPath("movieFile");
        String path = "C:\\nr\\dbws_intellij\\upload\\movieFile";
//        MAC 경로 다름 깃허브 충돌나면 위 부분을 체크해서 보면 됨.
        System.out.println(path);

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);

            MultipartRequest mr = new MultipartRequest(req, path, 1024*1024*20, "utf-8", new DefaultFileRenamePolicy());

            String title = mr.getParameter("title");
            String actor = mr.getParameter("actor");
            String story = mr.getParameter("story");
            String newImg = mr.getFilesystemName("newImg");
            String no = mr.getParameter("no");
            String oldImg = mr.getParameter("oldImg");
            String img = newImg;
            if(newImg == null){
                img = mr.getParameter("oldImg");
            }

            System.out.println(title);
            System.out.println(actor);
            System.out.println(story);
//            System.out.println(fileName);
            System.out.println(no);

            story = story.replaceAll("\r\n", "<br>");
            // 한줄 띄어쓰기 자바에서 변경

            pstmt.setString(1, title);
            pstmt.setString(2, actor);
            pstmt.setString(4, img);
            pstmt.setString(3, story);
            pstmt.setString(5, no);

            req.setAttribute("noo", no);

            if(pstmt.executeUpdate() == 1){
                System.out.println("edit success");
                if (newImg != null) { // 새로운 사진을 교체함. 이때 그 전 사진 삭제. 서버 용량 == 돈. 우리가 해야하는 것
                    File f = new File(path + "/" + oldImg);
                    f.delete();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(null, pstmt, con);
        }

    }

    public void paging(int pageNum, HttpServletRequest req) {
        req.setAttribute("currentPage", pageNum);
        int total = movies.size();
        int cnt = 3;

        // 페이지수
        int totalPage = (int)Math.ceil((double)total / cnt);
        req.setAttribute("totalPage", totalPage);

        int start = total - (cnt * (pageNum - 1));
        int end = (pageNum == totalPage) ? -1 : start - (cnt + 1);

        ArrayList<MovieDTO> items = new ArrayList<>();
        for (int i = start - 1; i > end; i--) {
            items.add(movies.get(i));
        }

        req.setAttribute("movies", items);



    }












}
