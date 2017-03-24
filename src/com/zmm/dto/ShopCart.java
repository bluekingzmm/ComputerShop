package com.zmm.dto;

public class ShopCart {
	
	/*
	 * 购物车信息
	 */

	private Computer com;//电脑信息
	private int com_id;
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
		ShopCart other = (ShopCart) obj;
		if (com_id != other.com_id)
			return false;
		return true;
	}
	public int getCom_id() {
		return com_id;
	}
	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}
	
	public Computer getCom() {
		return com;
	}
	public void setCom(Computer com) {
		this.com = com;
	}
	private int count;//购买数量

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public String toString() {
		return "ShopCart [com=" + com + ", count=" + count + "]";
	}
	
	
}
