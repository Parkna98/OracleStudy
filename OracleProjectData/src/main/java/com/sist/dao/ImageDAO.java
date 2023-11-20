package com.sist.dao;
import java.util.*;
import java.sql.*;

public class ImageDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.102:1521:XE";
	// 에러 => output => this.conn NULLPOINTEREXCEPION => url 확인 => 드라이버 확인
	public ImageDAO() {
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
	public void ImageInsert(ImageVO vo) {
		try {
			getConnection();
			String sql="INSERT INTO stayimage VALUES(stayimage_sino_seq.nextval,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getMain());
			ps.setString(2, vo.getSub1());
			ps.setString(3, vo.getSub2());
			ps.setString(4, vo.getSub3());
			ps.setString(5, vo.getSub4());

			ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
	}
}
