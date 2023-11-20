package com.sist.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.RoomDAO;
import com.sist.dao.RoomVO;

public class RoomMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RoomDAO dao=new RoomDAO();
		
		try {
			Document doc=Jsoup.connect("https://www.goodchoice.kr/product/result?sel_date=2023-11-27&sel_date2=2023-11-28&keyword=%EB%B0%98%EB%A0%A4%EB%8F%99%EB%AC%BC").get();
			Elements link=doc.select("div.listpage a");
			int idx=1;
			for(int i=0;i<link.size();i++) {
//				System.out.println(link.get(i).attr("href"));
				String subLink=link.get(i).attr("href");
				Document doc2=Jsoup.connect(subLink).get();
				int roomno=1;
				int price=0;
				int ii=0;
				// 방
				Elements rname=doc2.select("article.room_info strong.title");
				for(int j=0;j<rname.size();j++) {
					Element image=doc2.select("article.room_info ul.swiper-wrapper img").get(j);
					String im=image.attr("data-src");
					String name=rname.get(j).text();
					String sukbak=doc2.select("article.room_info div.info div.price strong").text();
					String sp="0";
					if(sukbak.contains("대실")) {
						Element noprice=doc2.select("article.room_info div.info div.price div").get(1+2*j);
						if(noprice.text().contains("문의")) {
							sp="0";
						}
						else {
							sp=doc2.select("article.room_info div.info div.price b").get(ii++).text();
						}
					}
					else {
						Element noprice=doc2.select("article.room_info div.info div.price div").get(j);
						if(noprice.text().contains("확인")) {
							sp="0";
						}
						else {
							sp=doc2.select("article.room_info div.info div.price b").get(ii++).text();
						}
					}
					price=Integer.parseInt(sp.replace("원", "").replace(",", ""));
					System.out.println("숙소번호: "+idx);
					System.out.println("방번호: "+roomno);
					System.out.println("이름: "+name);
					System.out.println("이미지: "+im);
					System.out.println("가격: "+price);
					
					RoomVO vo=new RoomVO();
					vo.setImage(im);
					vo.setName(name);
					vo.setPrice(price);
					vo.setRoomno(roomno);
					vo.setStayno(idx);
					dao.RoomInsert(vo);
					roomno++;
				}
				System.out.println("----------------------");
				idx++;
							
			}
				
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	
	}

}
