package com.sist.dao;
import java.util.*;
import java.sql.*;

public class RoomDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.102:1521:XE";
	// 에러 => output => this.conn NULLPOINTEREXCEPION => url 확인 => 드라이버 확인
	public RoomDAO() {
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
	public void RoomInsert(RoomVO vo) {
		try {
			getConnection();
			String sql="INSERT INTO roominfo VALUES(roominfo_rno_seq.nextval,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getRoomno());
			ps.setString(2, vo.getImage());
			ps.setString(3, vo.getName());
			ps.setInt(4, vo.getPrice());
			ps.setInt(5, vo.getStayno());
			
			ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
	}
}
