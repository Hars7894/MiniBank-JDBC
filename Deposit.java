package com.jdbcMiniBank;

import java.sql.*;

public class Deposit {
    private Connection con;

    public Deposit(Connection con) {
        this.con = con;
    }

    public void DepositMoney(String a_no, double amount) {
        String sql1 = "UPDATE users SET balance = balance + ? where a_no = ?";
        try (PreparedStatement ps = con.prepareStatement(sql1)) {
            ps.setDouble(1, amount);
            ps.setString(2, a_no);
            ps.executeUpdate();
            System.out.println("A/c-" + a_no + "     " + amount + "₨ Deposit Successful✅");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
