package com.jdbcMiniBank;

import java.sql.*;

public class ShowBalance {
    private Connection con;

    ShowBalance(Connection con) {
        this.con = con;
    }

    public void ChekBalance(String ac_no) {
        String sql = "select * from users where a_no = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ac_no);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    double balance = rs.getDouble("balance");
                    String name = rs.getString("name");
                    String ac = rs.getString("a_no");

                    String details = rs.getString("ph")
                            + " "
                            + rs.getString("mail")
                            + " "
                            + rs.getString("imf");
                    System.out.println(name + "---" + ac + "----" + "Rs " + balance);
                    System.out.println("-------details---------------" + details);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public double balance(String ac_no) {
        double Bal = 0;
        String sql = "select balance from users where a_no = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ac_no);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Bal = rs.getDouble("balance");
                }
            }
            return Bal;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Bal;

        }

    }
}
