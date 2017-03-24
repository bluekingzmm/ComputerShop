/**
 * 
 */
package com.zmm.dto;

/**
 * @author Administrator
 *
 */
public class OrderDetail {

	/*
	 * 订单详情
	 */

	private Long order_id;// 订单信息
	private int account;// 购买数量
	private int order_detail_id;
	private double buy_price;// 购买时价格
	private Computer com;
	



	@Override
	public String toString() {
		return "OrderDetail [order_id=" + order_id + ", account=" + account + ", order_detail_id=" + order_detail_id
				+ ", buy_price=" + buy_price + ", com=" + com + "]";
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	

	public int getOrder_detail_id() {
		return order_detail_id;
	}

	public void setOrder_detail_id(int order_detail_id) {
		this.order_detail_id = order_detail_id;
	}


	public double getBuy_price() {
		return buy_price;
	}




	public Computer getCom() {
		return com;
	}

	public void setCom(Computer com) {
		this.com = com;
	}

	public void setBuy_price(double buy_price) {
		this.buy_price = buy_price;
	}

	

}
