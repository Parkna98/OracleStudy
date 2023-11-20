package com.sist.dao;

public class RoomVO {
	private int rno,roomno;
	private int price,stayno;
	private String image,name;
	public int getRoomno() {
		return roomno;
	}
	public void setRoomno(int roomno) {
		this.roomno = roomno;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStayno() {
		return stayno;
	}
	public void setStayno(int stayno) {
		this.stayno = stayno;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
