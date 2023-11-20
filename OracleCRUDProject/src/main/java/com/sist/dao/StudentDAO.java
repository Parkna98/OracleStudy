package com.sist.dao;
import java.util.*;

import javax.naming.spi.DirStateFactory.Result;

import java.sql.*;
public class StudentDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	public StudentDAO() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
			// 오라클로 전송하는 명령어 => conn hr/happy
		}catch(Exception ex) {}
	}
	// 오라클 닫기
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			// OuputStream / BufferedReader
			if(conn!=null) conn.close();
			// Socket
		}catch(Exception ex) {}
	}
	// 기능
	// 1. 목록 => SELECT => 페이징 (자바,오라클=인라인뷰)
	//	=> 오라클만 연동 => 출력 => main, 브라우저로 전송
	// 	=> 데이터를 모아서 전송(목록) ArrayList
	public List<StudentVO> stdListData(){
		List<StudentVO> list=new ArrayList<StudentVO>();
		try {
			// 1. 오라클 연결
			getConnection();
			// 2. SQL문장을 만든다 (오라클로 전송)
			String sql="SELECT hakbun,name,kor,eng,math,"
					+"kor+eng+math,ROUND((kor+eng+math)/3.0,2),"
					+"RANK() OVER(ORDER BY (kor+eng+math) DESC)," 
					+"to_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') "
					+"FROM student";
			// => 웹은 모든 데이터가 공유 => 데이터는 반드시 오라클에 저장된 상태
			// => VueJS (VueX) / ReactJS (Redux)
			// ======> 데이터가 없는 상태 (데이터 관리 프로그램)
			// VO => {}, List => [] ==> [{},{},{}...] => JSON
			// XML => JSON ==> Ajax
			// 3. 오라클로 SQL전송
			ps=conn.prepareStatement(sql);
			// 4. 실행결과 저장
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				// 데이터를 모아서 전송 => MainClass
				StudentVO vo=new StudentVO();
				vo.setHakbun(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setKor(rs.getInt(3));
				vo.setEng(rs.getInt(4));
				vo.setMath(rs.getInt(5));
				vo.setTotal(rs.getInt(6));
				vo.setAvg(rs.getDouble(7));
				vo.setRank(rs.getInt(8));
				vo.setDbday(rs.getString(9));
				list.add(vo);
				
				
				
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			// 오라클 닫기
			disConnection();
		}
		return list;
	}
	// 2. 추가 => INSERT => COMMIT (AutoCommit)
	public void stdInsert(StudentVO vo) {
		try {
			getConnection();
			/*String sql="INSERT INTO student(hakbun,name,kor,eng,math) "
					+"VALUES((SELECT MAX(hakbun)+1 FROM student),'"
					+vo.getName()+"',"+vo.getKor()+","+vo.getEng()+","+vo.getMath()+")";
			System.out.println(sql);*/
			String sql="INSERT INTO student(hakbun,name,kor,eng,math) "
						+"VALUES((SELECT MAX(hakbun)+1 FROM student),?,?,?,?)";
			// prepared statement => 문장을 만들어놓고 ?에 값넣어서 처리
			// 받아야할 데이터가 많을 경우 ?를 여러개 써서 ps.set으로 처리
			// 값이 한개라면 +로 처리
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setInt(2, vo.getKor());
			ps.setInt(3, vo.getEng());
			ps.setInt(4, vo.getMath());
			// 데이터형 처리
			// setString ==> ' ' 자동으로 붙여줌
			// 실행
			ps.executeUpdate(); // => 데이터베이스가 변동 
			// SELECT => executeQuery ,  INSERT,UPDATE,DELETE => executeUpdate
			// ** executeUpdate ==> AutoCommit (executeQuery는 아님)ㄴ
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
	}
	// 3. 상세보기 => SELECT WHERE
	// 	  ----- 반드시 (Primary KEY값을 전송)
	public StudentVO stdDetailData(int hakbun) {
		StudentVO vo=new StudentVO();
		try {
			getConnection();
			String sql="SELECT hakbun,name,kor,eng,math,"
					+"kor+eng+math,ROUND((kor+eng+math)/3.0,2),"
					+"RANK() OVER(ORDER BY (kor+eng+math) DESC)," 
					+"to_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') "
					+"FROM student "
					+"WHERE hakbun="+hakbun;
			ps=conn.prepareStatement(sql);
			// 4. 실행결과 저장
			ResultSet rs=ps.executeQuery();
			
			rs.next();
			
			vo.setHakbun(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setKor(rs.getInt(3));
			vo.setEng(rs.getInt(4));
			vo.setMath(rs.getInt(5));
			vo.setTotal(rs.getInt(6));
			vo.setAvg(rs.getDouble(7));
			vo.setRank(rs.getInt(8));
			vo.setDbday(rs.getString(9));
			
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return vo;
	}
	// 4. 수정 => UPDATE => COMMIT
	// id는 수정X => 비활성화시키면서 수정들어감
	public StudentVO stdUpdateData(int hakbun) {
		StudentVO vo=new StudentVO();
		try {
			getConnection();
			String sql="SELECT hakbun,name,kor,eng,math "
						+"FROM student "
						+"WHERE hakbun="+hakbun;
			// 수정할 값을 먼저 보여줌
			// => 수정할 것만 수정
			
			// 대댓글 / 답변형 
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next(); // 커서를 데이터를 읽기위해 첫번째 데이터로 옮긴다 // executeQuery는 커서를 데이터 없는 마지막에 이동시킨다
			// 데이터 하나만 출력하기때문에 while문 쓰지 않는다
			// 목록을 가져오려면 while문을 쓴다
			vo.setHakbun(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setKor(rs.getInt(3));
			vo.setEng(rs.getInt(4));
			vo.setMath(rs.getInt(5));
			
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return vo;
	}
	
	public void stdUpdate(StudentVO vo) {
		try {
			getConnection();
			String sql="UPDATE student SET "
					+"name=?,kor=?,eng=?,math=? "
					+"WHERE hakbun=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setInt(2, vo.getKor());
			ps.setInt(3, vo.getEng());
			ps.setInt(4, vo.getMath());
			ps.setInt(5, vo.getHakbun());
			
			ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
	}
	// 5. 삭제 => DELETE => COMMIT
	public void stdDelete(int hakbun) {
		try {
			getConnection();
			String sql="DELETE FROM student "
						+"WHERE hakbun="+hakbun;
			ps=conn.prepareStatement(sql);
			// +hakbun 대신 ?를 주면 한문장 더 추가
			// ps.setInt(1,hakbun);
			ps.executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
	}
	
	public static void main(String[] args) {
		StudentDAO dao=new StudentDAO();
		StudentVO vo=new StudentVO();
		vo.setHakbun(8);
		vo.setName("이산");
		vo.setKor(90);
		vo.setEng(60);
		vo.setMath(80);
		dao.stdInsert(vo);
	}
	
}

