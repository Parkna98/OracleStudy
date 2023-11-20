package com.sist.main;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.*;

public class ImageMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImageDAO dao=new ImageDAO();
		try {
			Document doc=Jsoup.connect("https://www.goodchoice.kr/product/result?sel_date=2023-11-27&sel_date2=2023-11-28&keyword=%EB%B0%98%EB%A0%A4%EB%8F%99%EB%AC%BC").get();
			Elements link=doc.select("div.listpage a");
			
			for(int i=0;i<link.size();i++) {
//				System.out.println(link.get(i).attr("href"));
				String subLink=link.get(i).attr("href");
				Document doc2=Jsoup.connect(subLink).get();
				
				// 메인이미지
				Element m=doc2.selectFirst("div.top img");
				String ma=m.attr("data-src");
//				System.out.println(ma);
				
				// 서브1
				Element m1=doc2.selectFirst("div.top li.swiper-slide:eq(1) img");
				String s1=m1.attr("data-src");
//				System.out.println(s1);
				
				// 서브2
				Element m2=doc2.selectFirst("div.top li.swiper-slide:eq(1) img");
				String s2=m1.attr("data-src");
//				System.out.println(s1);
				
				// 서브3
				Element m3=doc2.selectFirst("div.top li.swiper-slide:eq(1) img");
				String s3=m1.attr("data-src");
//				System.out.println(s1);
				
				// 서브4
				Element m4=doc2.selectFirst("div.top li.swiper-slide:eq(1) img");
				String s4=m1.attr("data-src");
//				System.out.println(s1);
				
				ImageVO vo=new ImageVO();
				vo.setMain(ma);
				vo.setSub1(s1);
				vo.setSub2(s2);
				vo.setSub3(s3);
				vo.setSub4(s4);
				dao.ImageInsert(vo);
			}
			System.out.println("Save End...");
		}catch(Exception ex) {}
	}

}
