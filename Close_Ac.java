package com.jdbcMiniBank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Close_Ac {
    private Connection con;

    public Close_Ac(Connection con) {
        this.con = con;
    }

    public void close_A(String ac_no) {
        String sql = "delete from users where a_no = ? ";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ac_no);
            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println("A/c closed✅");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
