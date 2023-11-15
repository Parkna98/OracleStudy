package com.sist.main;
// JSP로 변경 => jsp file만들어서 안에 붙이고 형식살짝 수정하면 웹에 띄우기 가능
import java.util.*;
import com.sist.dao.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmpDAO dao=new EmpDAO();
		/*ArrayList<EmpVO> list=dao.empAlldate();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getHiredate()+" "
					+vo.getSal()+" "
					+vo.getDvo().getDname()+" "
					+vo.getDvo().getLoc()+" "
					+vo.getSvo().getGrade());
		}
		*/
		/*
		ArrayList<EmpVO> list=dao.subqueryEmpData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getDvo().getDname()+" "
					+vo.getDvo().getLoc()+" "
					+vo.getHiredate()+" "
					+vo.getSal());
			
		}*/
		ArrayList<EmpVO> list=dao.subqueryInEmpListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getDvo().getDname()+" "
					+vo.getDvo().getLoc()+" "
					+vo.getHiredate()+" "
					+vo.getSal());
			
		}
	}

}
