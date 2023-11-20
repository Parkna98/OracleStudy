package com.sist.dao;
import java.util.*;
import java.sql.*;

public class DetailDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.102:1521:XE";
	// 에러 => output => this.conn NULLPOINTEREXCEPION => url 확인 => 드라이버 확인
	public DetailDAO() {
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
	public void DetailInsert(DetailVO vo) {
		try {
			getConnection();
			String sql="INSERT INTO staydetail VALUES(staydetail_sdno_seq.nextval,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getAround());
			ps.setString(2, vo.getBasic());
			ps.setString(3, vo.getPetinfo());
			ps.setString(4, vo.getOther());
			
			ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
	}
}
