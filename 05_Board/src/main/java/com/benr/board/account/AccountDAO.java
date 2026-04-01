package com.benr.board.account;

import com.benr.board.main.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAO {

    public static final AccountDAO ADAO = new AccountDAO();

    private AccountDAO() {

    }

    public boolean loginCheck(HttpServletRequest req){
        AccountVO user = (AccountVO) req.getSession().getAttribute("user");
        if(user != null){
            req.setAttribute("loginPage", "jsp/account/loginOK.jsp");
            return true;
        } else {
            req.setAttribute("loginPage", "jsp/account/login.jsp");
            return false;
        }
    }


    public void login(HttpServletRequest req) {
        // 1. 값 or db
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");
        if (id == null) {
            id = (String) req.getAttribute("iddd");
        }

        // db여기에 있는 계정이랑 비교
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from login_test where l_id = ?";
        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();
            String msg = null;
            if (rs.next()) {
                if (rs.getString("l_pw").equals(pw)) {
                    // 로그인 성공
                    System.out.println("로그인 성공");
                    msg = "로그인 성공";

                    AccountVO accountVO = new AccountVO();
                    accountVO.setId(rs.getString("l_id"));
                    accountVO.setPw(rs.getString("l_pw"));
                    accountVO.setName(rs.getString("l_name"));
//                    req.setAttribute("user", accountVO);
                    HttpSession hs = req.getSession();
                    hs.setAttribute("user", accountVO);
                    hs.setMaxInactiveInterval(5 * 60);

                } else {
                    // 비번에러
                    System.out.println("비번 에러");
                    msg = "비번 에러";

                }
            } else {
                // 유저 없음
                System.out.println("유저 없음");
                msg = "유저 없음";

            }
            req.setAttribute("msg", msg);




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs, pstmt, con);
        }
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
//        session.invalidate(); 세션초기화, 함부로 쓰면 안 됨. 다른 세션도 다 초기화 할 수 있음
        session.removeAttribute("user"); // 삭제
//            session.setAttribute("user", null);
    }

    public boolean delUser(HttpServletRequest request) {
        AccountVO user = (AccountVO) request.getSession().getAttribute("user");
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "delete login_test where l_id = ?";
    try {
        if (user.getPw().equals(request.getParameter("pw"))){

        con = DBManager.connect();
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, user.getId());

        if (pstmt.executeUpdate() == 1) {
            System.out.println("delete success");
            logout(request);
            return true;
        }
        } else {
            return false; // 안 된단 의미의 0
        }

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        DBManager.close(null, pstmt,con);
    }

        return false;
    }

    public void editUser(HttpServletRequest req) {
        AccountVO user = (AccountVO) req.getSession().getAttribute("user");
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "update login_test set l_pw = ?, l_name = ? where l_id = ?";
        try {


                con = DBManager.connect();
                pstmt = con.prepareStatement(sql);
                String name = req.getParameter("name");
                String pw = req.getParameter("pw");

                pstmt.setString(1, pw);
                pstmt.setString(2, name);
                pstmt.setString(3, user.getId());

                pstmt.setString(1, user.getId());

                if (pstmt.executeUpdate() == 1) {
                    System.out.println("update success");
                    req.setAttribute("iddd", user.getId());
                    // 로그인 다시
                    login(req);

                    // 세션 업데이트
                    user.setName(name);
                    user.setPw(pw);
                }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(null, pstmt,con);
        }


    }
}
