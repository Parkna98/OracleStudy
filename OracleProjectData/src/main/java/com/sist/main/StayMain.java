package com.sist.main;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.*;

public class StayMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StayDAO dao=new StayDAO();
		try {
			Document doc=Jsoup.connect("https://www.goodchoice.kr/product/result?sel_date=2023-11-27&sel_date2=2023-11-28&keyword=%EB%B0%98%EB%A0%A4%EB%8F%99%EB%AC%BC").get();
			Elements link=doc.select("div.listpage a");
			// 평점
			Elements score=doc.select("div.stage p.score em");
			
			// 유형
			Elements type=doc.select("div.stage div.right_badges span");
			
			// 대략주소
			Elements adr=doc.select("div.name p:eq(2)");
			
			// 리뷰수
			Elements count=doc.select("div.name p.score");
//			System.out.println(link.size());
			for(int i=0;i<link.size();i++) {
//				System.out.println(link.get(i).attr("href"));
				String subLink=link.get(i).attr("href");
				Document doc2=Jsoup.connect(subLink).get();
				
				// 업체명
				Element name=doc2.selectFirst("div.info h2");
//				System.out.println(name.text());
				
				// 평점
//				Element score=doc2.selectFirst("div.info div.score_cnt span");
//				if(score==null) continue;
//				System.out.println(score.text());
//				System.out.println(score.get(i).text());
				
				// 주소
				Element address=doc2.selectFirst("div.info p.address");
//				System.out.println(address.text());
				
				// 대략 주소
//				System.out.println(adr.get(i).text());
				
				// 가격
				int pri=0;
				Element price=doc2.selectFirst("div.info div.price b");
				if(price==null) {
//					System.out.println("정보없음");
					pri=0;
				}
				else {
					String p=price.text();
					pri=Integer.parseInt(p.substring(0,p.indexOf("원")).replaceAll(",", ""));
//					System.out.println(pp);
				}
				
				// 유형
//				System.out.println(type.get(i).text());
				
				// 리뷰수
				String r=count.get(i).text();
				
				StayVO vo=new StayVO();
				vo.setType(type.get(i).text());
				vo.setName(name.text());
				vo.setAddr(adr.get(i).text());
				vo.setDetailaddr(address.text());
				vo.setScore(Double.parseDouble(score.get(i).text()));
				vo.setPrice(pri);
				vo.setRcount(Integer.parseInt(r.substring(r.indexOf("(")+1,r.indexOf(")"))));
				dao.stayInsert(vo);
				
			}
			System.out.println("Save End...");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
