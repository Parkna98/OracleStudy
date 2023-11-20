package com.sist.main;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.*;

public class DetailMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DetailDAO dao=new DetailDAO();
		
		try {
			Document doc=Jsoup.connect("https://www.goodchoice.kr/product/result?sel_date=2023-11-27&sel_date2=2023-11-28&keyword=%EB%B0%98%EB%A0%A4%EB%8F%99%EB%AC%BC").get();
			Elements link=doc.select("div.listpage a");
			
			for(int i=0;i<link.size();i++) {
//				System.out.println(link.get(i).attr("href"));
				String subLink=link.get(i).attr("href");
				Document doc2=Jsoup.connect(subLink).get();
				
				// 상세정보
				Elements etc=doc2.select("section.default_info h3");
				String a1="";
				String a2="";
				String a3="";
				String a4="";
				String de=etc.get(0).text();
//				System.out.println(de);
				if(de.contains("사장")) {
					for(int j=1;j<etc.size();j++) {
						String ss=etc.get(j).text();
						if(ss.contains("주변")) {
							Element around=doc2.select("section.default_info ul").get(j-1);
							a1=around.text();
						}
						else if(ss.contains("기본")|ss.contains("객실내부")) {
							Element basic=doc2.select("section.default_info ul").get(j-1);
							a2=basic.text();
						}
						else if(ss.contains("펫")|ss.contains("반려")|ss.contains("애견")|ss.contains("동물")|ss.contains("프런트")) {
							Element pet=doc2.select("section.default_info ul").get(j-1);
							a3=pet.text();
						}
						else if(ss.contains("기타")|ss.contains("추가")) {
							Element other=doc2.select("section.default_info ul").get(j-1);
							a4=other.text();
						}
					}
				}
				else {
					for(int j=0;j<etc.size();j++) {
						String ss=etc.get(j).text();
						if(ss.contains("주변")) {
							Element around=doc2.select("section.default_info ul").get(j);
							a1=around.text();
						}
						else if(ss.contains("기본")|ss.contains("객실내부")) {
							Element basic=doc2.select("section.default_info ul").get(j);
							a2=basic.text();
						}
						else if(ss.contains("펫")|ss.contains("반려")|ss.contains("애견")|ss.contains("동물")|ss.contains("프런트")) {
							Element pet=doc2.select("section.default_info ul").get(j);
							a3=pet.text();
						}
						else if(ss.contains("기타")|ss.contains("추가")) {
							Element other=doc2.select("section.default_info ul").get(j);
							a4=other.text();
						}
					}
				}
				System.out.println("주변정보"+a1);
				System.out.println("기본정보"+a2);
				System.out.println("펫정보"+a3);
				System.out.println("기타정보"+a4);
				System.out.println("--------------");
				DetailVO vo=new DetailVO();
				vo.setAround(a1);
				vo.setBasic(a2);
				vo.setPetinfo(a3);
				vo.setOther(a4);
				dao.DetailInsert(vo);
			}
			System.out.println("Save End...");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}