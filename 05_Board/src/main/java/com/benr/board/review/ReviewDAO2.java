package com.benr.board.review;

import com.benr.board.main.DBManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class ReviewDAO2 {

    public static final ReviewDAO2 RDAO = new ReviewDAO2();

    private ReviewDAO2() {

    }

    public ArrayList<ReviewVO> showAllReview(HttpServletRequest request) {


        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from review_test order by r_date";

        try {

            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            ReviewVO reviewVO = null;
            ArrayList<ReviewVO> reviews = new ArrayList<>();

            while (rs.next()) {
                int no = rs.getInt("r_no");
                String title = rs.getString("r_title");
                String txt = rs.getString("r_txt");
                Date r_date = rs.getDate("r_date");
                reviewVO = new ReviewVO();
                reviewVO.setNo(no);
                reviewVO.setTitle(title);
                reviewVO.setTxt(txt);
                reviewVO.setDate(r_date);
                reviews.add(reviewVO);

            }
            System.out.println(reviews);
            return reviews;
//            request.setAttribute("reviews", reviews);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, pstmt, con);
        }

        return null;
    }

    public void addReview(HttpServletRequest req) {
        Connection con = null;
        PreparedStatement pstmt = null;

        String sql = "insert into review_test values (review_test_seq.nextval, ?, ?, sysdate)";

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            req.setCharacterEncoding("UTF-8");
            pstmt.setString(1, req.getParameter("title"));
            pstmt.setString(2, req.getParameter("txt"));

            if (pstmt.executeUpdate() == 1) {
                System.out.println("add review success");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(null, pstmt, con);
        }

    }

    public void getReview(HttpServletRequest request) {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from review_test where r_no = ?";

        try {

            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, request.getParameter("pk"));
            rs = pstmt.executeQuery();
            ReviewVO reviewVO = null;

            if (rs.next()) {
                int no = rs.getInt("r_no");
                String title = rs.getString("r_title");
                String txt = rs.getString("r_txt");
                Date r_date = rs.getDate("r_date");
                reviewVO = new ReviewVO();
                reviewVO.setNo(no);
                reviewVO.setTitle(title);
                reviewVO.setTxt(txt);
                reviewVO.setDate(r_date);

            }
            request.setAttribute("review", reviewVO);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, pstmt, con);
        }

    }

    public void updateReview(HttpServletRequest req) {

        Connection con = null;
        PreparedStatement pstmt = null;

        String sql = "update review_test set r_title = ?, r_txt = ? where r_no = ?";
//        r_date=sysdate 변경된 날짜 반영하고 싶을 땐
        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            req.setCharacterEncoding("UTF-8");

            pstmt.setString(1, req.getParameter("title"));
            pstmt.setString(2, req.getParameter("txt"));
            pstmt.setString(3, req.getParameter("no"));

            if (pstmt.executeUpdate() == 1) {
                System.out.println("update review success");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(null, pstmt, con);
        }

    }

    public void delReview(HttpServletRequest request) {
        Connection con = null;
        PreparedStatement pstmt = null;

        String sql = "delete review_test where r_no = ?";

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, request.getParameter("no"));

            if (pstmt.executeUpdate() == 1) {
                System.out.println("delete review success");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(null, pstmt, con);
        }


    }

    public void paging(int pageNum, HttpServletRequest req) {
        req.setAttribute("currentPage", pageNum);
        ArrayList<ReviewVO> reviews = showAllReview(req);
        int total = reviews.size();
        int cnt = 3;

        // 페이지수
        int totalPage = (int) Math.ceil((double) total / cnt);
        req.setAttribute("totalPage", totalPage);

        int start = total - (cnt * (pageNum - 1));
        int end = (pageNum == totalPage) ? -1 : start - (cnt + 1);

        ArrayList<ReviewVO> items = new ArrayList<>();
        for (int i = start - 1; i > end; i--) {
            items.add(reviews.get(i));
        }

        req.setAttribute("reviews", items);


    }

    public ArrayList<String> searchReview(HttpServletRequest request) {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from review_test where r_title like '%'||?||'%'";

        try {

            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, request.getParameter("reviewTitle")); // ajax 요청부분 보고 작성
            // 밑에 요청 전에 위에 sql완성문 아니니까 채워주기
            rs = pstmt.executeQuery();
            ReviewVO reviewVO = null;
            ArrayList<String> reviews = new ArrayList<>(); // String으로 제네릭 바꾸기 이유?

            while (rs.next()) {
                int no = rs.getInt("r_no");
                String title = rs.getString("r_title");
                String txt = rs.getString("r_txt");
                Date r_date = rs.getDate("r_date");
                reviewVO = new ReviewVO();
                reviewVO.setNo(no);
                reviewVO.setTitle(title);
                reviewVO.setTxt(txt);
                reviewVO.setDate(r_date);
                reviews.add(reviewVO.toJSON()); // json문법으로 바꾸기 위함. VO가서 제이슨지슨 추가해주기

            }
            System.out.println(reviews);
            return reviews;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, pstmt, con);
        }


        return null;
    }
}
