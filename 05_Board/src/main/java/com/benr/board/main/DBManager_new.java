package com.benr.board.main;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager_new {
    private static BasicDataSource dataSource;
    static {

        // 이게 홈체이지 속도 더 빠름 리뷰 뮤비 비교! 리뷰가 더 빠름
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@10.1.82.127:1521:XE");
        dataSource.setUsername("c##benr1004");
        dataSource.setPassword("benr1004");
        dataSource.setMinIdle(10);        // 최소 유휴 커넥션
        dataSource.setMaxIdle(20);        // 최대 유휴 커넥션
        dataSource.setMaxOpenPreparedStatements(100); // 풀에서 열린 최대 준비된 sql문 개수

    }
    public static Connection connect() throws SQLException {
        return dataSource.getConnection();
    }
    public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            pstmt.close();
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}