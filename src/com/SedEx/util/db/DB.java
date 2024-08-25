package com.SedEx.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
	//db 접속 데이터
	private static final String driver="oracle.jdbc.driver.OracleDriver";
	private static final String url="jdbc:oracle:thin:@192.168.0.57:1521:xe";
	private static final String id="sedex";
	private static final String pw="sedex";
	
	static {
		try {
			Class.forName(driver);
			System.out.println("1. 드라이버 확인");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("오류내용: "+e.getClass().getSimpleName());
			System.out.println("프로그램 종료");
			System.exit(1);
		}
		
	}
	
	//getConnection, close
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, id, pw);
	}
	
	public static void close(Connection con, PreparedStatement pstmt) throws SQLException {
		if(con!=null) con.close();
		if(pstmt!=null) pstmt.close();
	}
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		close(con, pstmt);
		if(con!=null) con.close();
	}
}