package com.sist.dao;
import java.util.*;
import java.sql.*;

public class StayDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.102:1521:XE";
	// 에러 => output => this.conn NULLPOINTEREXCEPION => url 확인 => 드라이버 확인
	public StayDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
	}
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	public void stayInsert(StayVO vo) {
		try {
			getConnection();
			String sql="INSERT INTO stayinfo VALUES(stayinfo_stay_no_seq.nextval,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getType());
			ps.setString(2, vo.getName());
			ps.setDouble(3, vo.getScore());
			ps.setString(4, vo.getAddr());
			ps.setString(5, vo.getDetailaddr());
			ps.setInt(6, vo.getPrice());
			ps.setInt(7, vo.getRcount());
			
			ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
	}
}
