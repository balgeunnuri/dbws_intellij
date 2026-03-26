package com.benr.board.main;

import java.sql.*;

public class DBManager {
    public static Connection connect() throws SQLException {

        String url = "jdbc:oracle:thin:@10.1.82.127:1521:XE";
        // 클라우드
//        String url2 = "jdbc:oracle:thin:@eg6skguj9kvbijiu_medium?TNS_ADMIN=C:/mzz/Wallet_EG6SKGUJ9KVBIJIU";

        return DriverManager.getConnection(url,"c##benr1004", "benr1004");

    }

    public static void close(ResultSet rs, PreparedStatement ps, Connection con){
        try {

        if (rs != null) {
            rs.close();

        }
        if (ps != null) {
                ps.close();

            }
        if (con != null) {
             con.close();

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
