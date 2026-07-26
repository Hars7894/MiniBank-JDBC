package com.jdbcMiniBank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Withdraw {
    private Connection con;

    public Withdraw(Connection con) {
        this.con = con;
    }

    public void withdrawMoney(String a_no, double amount) {
        String sql = "select balance from users where a_no = ?";
        String sql1 = "update users set balance = balance - ? where a_no = ?";
        try (PreparedStatement ps = con.prepareStatement(sql);
             PreparedStatement ps1 = con.prepareStatement(sql1)) {
            ps.setString(1, a_no);
            ps1.setDouble(1, amount);
            ps1.setString(2, a_no);
            double bal = 0;
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    bal = rs.getDouble("balance");
                }
            }
            if (amount <= bal) {
                ps1.executeUpdate();
                System.out.println(amount + " withdrawal succeed✅✅");
            } else {
                System.out.println("insufficient fund❌❌");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
