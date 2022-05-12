package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.beans.Login;

public class DB {
	private String url = "jdbc:mysql://localhost:3306/CMS_DB71524";
	private String username = "root";
	private String password = "Password123";
	private String driver = "com.mysql.cj.jdbc.Driver";
	private Connection con;

	private void dbConnect() {

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url, username, password);
			System.out.println("conn sucess");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void dbClose() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean checkLogin(Login login) {
		dbConnect();
		boolean status = false;
		
		String sql="select * from login where username=? and password=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, login.getUsername());
			pstmt.setString(2, login.getPassword());
			
			ResultSet rst =   pstmt.executeQuery();
			status = rst.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbClose();
		return status;
	}

	public String fetchNameByUsername(String username) {
		dbConnect();
		 String name="";
		
		String sql="select vendor_name from vendor v,login l where v.login_id = l.login_id "
				+ "AND username=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			 
			ResultSet rst =   pstmt.executeQuery();
			while(rst.next()) {
				name = rst.getString("vendor_name");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dbClose();
		return name;
		 
	}

}
