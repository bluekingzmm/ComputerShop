package com.zmm.dto;

import java.util.Date;

public class Computer {

	/*
	 * ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ï¢
	 */
	private int com_id;
	private Brand brand;
	private String model;
	private String color;
	private double price;
	private double rel_price;
	private String imgPath;
	


	private Date reg_date;
	private String pixels;
	private Cpu cpu;
	private Screen screen;
	private String ram;
	private int status;
	private int num;// ¿â´æ
	private String content;//ÄÚÈÝ
	
	

	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getCom_id() {
		return com_id;
	}

	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}

	public String getModel() {
		return model;
	}

	public void setModer(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRel_price() {
		return rel_price;
	}

	public void setRel_price(double rel_price) {
		this.rel_price = rel_price;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public String getPixels() {
		return pixels;
	}

	public void setPixels(String pixels) {
		this.pixels = pixels;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Cpu getCpu() {
		return cpu;
	}

	public void setCpu(Cpu cpu) {
		this.cpu = cpu;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + com_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (com_id != other.com_id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Computer [com_id=" + com_id + ", brand=" + brand + ", model=" + model + ", color=" + color + ", price="
				+ price + ", rel_price=" + rel_price + ", imgPath=" + imgPath + ", reg_date=" + reg_date + ", pixels="
				+ pixels + ", cpu=" + cpu + ", screen=" + screen + ", ram=" + ram + ", status=" + status + ", num="
				+ num + ", content=" + content + "]";
	}

	

}
