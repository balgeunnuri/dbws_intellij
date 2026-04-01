package com.benr.board.review;

import com.benr.board.main.DBManager;
import com.benr.board.main.DBManager_new;
import com.benr.board.movie.MovieDTO;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ReviewDAO {

    public static final ReviewDAO RDAO = new ReviewDAO();
    public Connection con = null;

    private ReviewDAO() {
        try {
            con = DBManager_new.connect();
            // new로 한 게 더 빠름
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ReviewVO> showAllReview(HttpServletRequest request) {


        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from review_test order by r_date";

        try {

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
        PreparedStatement pstmt = null;

        String sql = "insert into review_test values (review_test_seq.nextval, ?, ?, sysdate)";

        try {
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

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from review_test where r_no = ?";

        try {

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

        PreparedStatement pstmt = null;

        String sql = "update review_test set r_title = ?, r_txt = ? where r_no = ?";
//        r_date=sysdate 변경된 날짜 반영하고 싶을 땐
        try {
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
        PreparedStatement pstmt = null;

        String sql = "delete review_test where r_no = ?";

        try {
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
}
