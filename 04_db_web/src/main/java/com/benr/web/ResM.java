package com.benr.web;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ResM {
    public static void showAllRes(HttpServletRequest request) {
        // 값받기 or db
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBManager.connect();
            String sql = "select * from restaurant_test";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            ResVO resVO = null;
            ArrayList<ResVO> restaurants = new ArrayList<>();

            while (rs.next()) {
                resVO = new ResVO();
                resVO.setNo(rs.getInt("r_no"));
                resVO.setName(rs.getString("r_name"));
                resVO.setPlace(rs.getString("r_place"));
                resVO.setImg(rs.getString("r_img"));

                restaurants.add(resVO);
            }
            request.setAttribute("restaurants", restaurants);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, pstmt, con);

        }

    }

    public static void addRes(HttpServletRequest req)  {
        // 1. 값 받기 db세팅
        String savePath = req.getServletContext().getRealPath("upload");
        System.out.println(savePath);

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            MultipartRequest mr = new MultipartRequest(req, savePath, 1024*1024*20, "utf-8", new DefaultFileRenamePolicy());

            String name = mr.getParameter("name");
            String place = mr.getParameter("place");
            String img = mr.getFilesystemName("img");

            String sql = "insert into restaurant_test values(restaurant_test_seq.nextval,?,?,?)";

            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, place);
            pstmt.setString(3, img);

            int result = pstmt.executeUpdate();
            System.out.println("결과: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(null, pstmt, con);

        }
    }

    public static void delRes(HttpServletRequest request) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBManager.connect();
            String sql = "delete restaurant_test where r_no = ?";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, request.getParameter("no"));

            if (pstmt.executeUpdate() == 1) {
                System.out.println("delete success");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(null, pstmt, con);

        }
    }

    public static void upResName(HttpServletRequest request) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBManager.connect();
            String sql = "update restaurant_test set r_name = ? where r_no = ?";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, request.getParameter("n"));
            pstmt.setString(2, request.getParameter("pk"));

            if (pstmt.executeUpdate() == 1) {
                System.out.println("update success");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(null, pstmt, con);

        }

    }
}
