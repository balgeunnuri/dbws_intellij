package com.benr.web;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberDAO {
    public static void getAllMember(HttpServletRequest request) {
        // 1. 값 받기 or db세팅


        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from member_test";

        try {

            conn = DBManager.connect();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            int no,age;
            String name;
            MemberDTO memberDTO = null;
            ArrayList<MemberDTO> members = new ArrayList<>();
            while (rs.next()) {
                no = rs.getInt("m_no");
                name = rs.getString("m_name");
                age = rs.getInt(3);
                memberDTO = new MemberDTO(no,name,age);
                members.add(memberDTO);
            }
            System.out.println(members);
            request.setAttribute("members", members);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, pstmt, conn);
        }


    }

    public static void addMember(HttpServletRequest req) {
        // 1. 값 받기 or db세팅

        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into member_test values (member_test_seq.nextval, ?, ?)";

        try {

            req.setCharacterEncoding("UTF-8");

            conn = DBManager.connect();
            pstmt = conn.prepareStatement(sql);

            String name = req.getParameter("n");
            String age = req.getParameter("a");
            pstmt.setString(1, name);
            pstmt.setString(2, age);

            if (pstmt.executeUpdate() == 1){
                System.out.println("add member success");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(null, pstmt, conn);
        }


    }

    public static void delMember(HttpServletRequest req) {
        //  값 받기 or db세팅 : CD비슷하니까 걍 위에서 긁어옴.
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "delete member_test where m_no=?";

        try {


            conn = DBManager.connect();
            pstmt = conn.prepareStatement(sql);

            String no = req.getParameter("num");
            pstmt.setString(1, no);

            if (pstmt.executeUpdate() == 1){
                System.out.println("del member success");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(null, pstmt, conn);
        }

    }

    public static void updateMemberName(HttpServletRequest request) {
        // 1. 값 or db
        //  값 받기 or db세팅 : CD비슷하니까 걍 위에서 긁어옴.
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update member_test set m_name = ? where m_no = ?";

        try {


            conn = DBManager.connect();
            pstmt = conn.prepareStatement(sql);

            String no = request.getParameter("num");
            String name = request.getParameter("name");
            pstmt.setString(1, name);
            pstmt.setString(2, no);

            if (pstmt.executeUpdate() == 1){
                System.out.println("name update success");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(null, pstmt, conn);
        }



    }
}
