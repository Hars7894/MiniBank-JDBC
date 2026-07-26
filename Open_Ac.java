package com.jdbcMiniBank;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Open_Ac {
    private Connection con;

    public Open_Ac(Connection con) {
        this.con = con;
    }

    public void open(String ac_no, String name, String mail, String ph, String imf, double balance) {
        String sql = "insert into users (a_no,name,mail,ph,imf,balance) values (?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,ac_no);
            ps.setString(2,name);
            ps.setString(3,mail);
            ps.setString(4,ph);
            ps.setString(5,imf);
            ps.setDouble(6,balance);
            ps.executeUpdate();
            System.out.println("Account open successfully✅");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
