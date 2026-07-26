package com.jdbcMiniBank;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Emp {
    private Connection con;

    public Emp(Connection con) {
        this.con = con;
    }

    public boolean exist(String id, String E_password) {
        boolean find = false;
        String sql = "select password from emp where e_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String password = rs.getString("password");
                    return password.equals(E_password);
                }
            }
            return find;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return find;
        }
    }
}
