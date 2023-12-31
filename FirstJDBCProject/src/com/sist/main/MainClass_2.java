package com.sist.main;
/*
		1) 
			Web 개발 (사이트)
			
			출력 위치 => 브라우저
					  ------ 오라클 연동하는 프로그램이 없다
					  	HTML / CSS / JavaSript
					  	=> 외의 모든 언어는 브라우저 일반 텍스트
					  	
			브라우저 <=====> 자바 <=====> 오라클
					전송			읽기
								JDBC => Java Database Connective
								java.sql
								=> 오라클 연결하는 프로그램은 
								   자바에 존재하지 않는다
								   => 외부라이브러리 => ojdbc8.jar
								파일 : => xml, json, csv
			=> 실무(X) => 입문
			=> 1차 프로젝트 : 화면 UI / 데이터베이스 (SQL)
				HTML/CSS/JavaScript 
				=> JSP => MVC/Ajax
			   ----------------------------------- 흐름
			=> 2차 프로젝트 : Spring(의존성이 약한 프로그램)
						  -------- 보안 / 권한 => ORM
				=> JSP => MVC / VueJS
				=> MyBatis
			=> 3차 프로젝트 : 신기술 (Back / Front)
						  		--------------
						  		 ㅣ 서버가 여러개 MSA (기능별로 만들어서 찾아서 수행) (한 서버가 무너져도 전체가 무너지지 않게)
				=> Spring Boot / Spring Cloud(MSA)
				=> MySQL, React(Redux), JPA
			--------------------------------------------------------
			MyBatis / JPA ***
					 ----  => 데이터베이스 연동
		
			=> 자바
				프로그램의 핵심 : 데이터 관리
				변수 / 배열 / 클래스 / 파일 / 오라클 / XML / JSON
				=> 데이터베이스 
				=> 머신러닝 / 딥러닝
					=> 데이터 수집 / 데이터 전처리 / 분석
			---------------------------------------------------------
			=> 프로젝트 
				=> 벤치마킹 (아이템) 
					=> 예약(예매), 추천, 결제
					--------------------
						ㅣ => 출력할 데이터 (데이터베이스에 값넣기)
				=> 요구사항 분석
				   ---------- 필요한 데이터 (크롤링)
				=> 오라클에 저장 (데이터베이스 설계)
				=> 화면UI
				=> 구현 
				=> 테스트 
				=> 배포 (발표) => 호스팅 (AWS)
				
			=> 문법
				= 연산자 / 제어문
				= 재사용 => 메소드
				---------------
					같은 기능 => 묶어주기
					---------------- => 클래스
					클래스 : 관련된 데이터를 묶는다 => VO
					 			=> 캡슐화 => 변수 은닉화 (private)
					 			=> 다른 클래스나 브라우저에서 접근불가
					 			=> 변수이용을 위해 메소드 사용 => getter/setter
						   => 사용자정의 데이터형
						   		=> CRUD => 액션클래스
						   			=> 메소드로 만 만든다
						   			=> ~Manager
						   			=> ~DAO (오라클 연동 : Data Access)
						   관련된 기능을 묶는다 => Manager
						   => 핵심 => 재사용 (상속/포함)
						   					  ---- 웹에서 상속은 거의 없고 포함이 상당수
						   					  => 상속없이 단독으로 사용하는 클래스 => POJO 클래스
						   => 포함 / 상속 => 수정, 추가 	
						   ------------ 객체지향의 3대요소
						   => 관련된 클래스가 여러개 있는 경우
						   	  ----------------------- ex) 게시판,갤러리게시판,댓글형게시판 ...
						   	  => 인터페이스를 이용해서 1개로 제어 
						   	  List list=new ArrayList()
						   	  	   list=new Vector()
						   	  	   list=new LinkedList()
						   ** Q. 인터페이스의 개념? 관련된 클래스가 여러개일 경우 한번에 모아 1개의 인터페이스로 제어
						   		 인터페이스의 문법? 
						   		 객체란? 현실과 비현실을 단순화한것
						   		 객체지향의 3대요소? 
						   		 
						   *** 여러개 데이터
						   	   변수 / 배열 / 클래스 => 한개의 이름으로 어떻게 제어
						   
			리턴형 => 1
			
			emp => 사원 정보
			=> empno 사번 => int 
			=> ename 이름 => String
			=> job 	 직위 => String
			=> hiredate 입사일 => Date
			=> sal 	 급여 => int
			=> comm  성과급 => int
			=> mgr 	 사수의 사번 => int
			=> deptno 부서번호 => int
			
			데이터를 묶지않으면 모든 정보에 대한 메소드를 각각 만들어야함
			=> 데이터 묶어서 VO를 이용해 한번에 갖고오자!!
			=> public EmpVO getEmpData(){
					
			   }
			   
			
 */
public class MainClass_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
