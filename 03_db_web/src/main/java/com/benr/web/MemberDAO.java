package com.benr.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberDAO {
    public static ArrayList<MemberDTO> getAllMember() {
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
            return members;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, pstmt, conn);
        }


        return null;
    }

    public static void addMember(MemberDTO member) {
        // 1. 값 받기 or db세팅

        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into member_test values (member_test_seq.nextval, ?, ?)";

        try {

//            req.setCharacterEncoding("UTF-8");

            conn = DBManager.connect();
            pstmt = conn.prepareStatement(sql);

            String name = member.getName();
            String age = member.getAge() + "";
//            ""붙이면 형변환 스트링이됨
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

    public static void delMember(MemberDTO member) {
        //  값 받기 or db세팅 : CD비슷하니까 걍 위에서 긁어옴.
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "delete member_test where m_no=?";

        try {


            conn = DBManager.connect();
            pstmt = conn.prepareStatement(sql);

            int no = member.getNo();
            pstmt.setInt(1, no);

            if (pstmt.executeUpdate() == 1){
                System.out.println("del member success");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(null, pstmt, conn);
        }

    }

    public static void updateMemberName(MemberDTO member) {
        // 1. 값 or db
        //  값 받기 or db세팅 : CD비슷하니까 걍 위에서 긁어옴.
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update member_test set m_name = ? where m_no = ?";

        try {


            conn = DBManager.connect();
            pstmt = conn.prepareStatement(sql);

            int no = member.getNo();
            String name = member.getName();
            pstmt.setString(1, name);
            pstmt.setInt(2, no);

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
