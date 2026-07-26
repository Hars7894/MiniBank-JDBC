package com.jdbcMiniBank;

import java.sql.*;

public class Exist_Account {
    private Connection con;

    Exist_Account(Connection con) {
        this.con = con;
    }

    public boolean Exist(String Account) {
        boolean find = false;
        String Sql1 = "SELECT * FROM users where a_no = ?";
        try (PreparedStatement ps = con.prepareStatement(Sql1)) {
            ps.setString(1, Account);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
