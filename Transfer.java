package com.jdbcMiniBank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transfer {
    private Connection con;

    public Transfer(Connection con) {
        this.con = con;
    }

    public void TransferMoney(String ac1,String ac2,double amount){
        ShowBalance showBalance = new ShowBalance(con);
        String sql1 = "update users set balance = balance - ? where a_no = ?";
        String sql2 = "update users set balance = balance + ? where a_no = ?";
        try (PreparedStatement ps1 = con.prepareStatement(sql1);
        PreparedStatement ps2 = con.prepareStatement(sql2)){
            double suf_fund = showBalance.balance(ac1);
            con.setAutoCommit(false);

            if (amount <= suf_fund){
                ps1.setDouble(1,amount);
                ps1.setString(2,ac1);
                ps2.setDouble(1,amount);
                ps2.setString(2,ac2);
                ps1.executeUpdate();
                ps2.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                System.out.println("Transfer successful ✅");
            }else {
                System.out.println("insufficient fund ❌");
                con.rollback();
                con.setAutoCommit(true);

            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
