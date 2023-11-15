<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*,com.sist.dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
	// system은 도스창 / 웹에띄우려면 out.println쓰고
	// <br> 태그를 주면 한줄출력후 다음줄로 가서 출력
	EmpDAO dao=new EmpDAO();
	ArrayList<EmpVO> list=dao.empAlldate();
	for(EmpVO vo:list) {
		out.println(vo.getEmpno()+" "
				+vo.getEname()+" "
				+vo.getJob()+" "
				+vo.getHiredate()+" "
				+vo.getSal()+" "
				+vo.getDvo().getDname()+" "
				+vo.getDvo().getLoc()+" "
				+vo.getSvo().getGrade()+"<br>");
	}
	%>
</body>
</html>