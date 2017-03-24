/**
 * 
 */
package com.zmm.dto;


/**
 * @author Administrator 订单信息
 */
public class Order {
	private Long orderId;
	private User user;
	private String submitTime;// 提交订单时间
	private double totalPrice;
	// 是否已支付
	private int isPay;
	// 是否已送货
	private int isDeliver;
	
	private String label;//备注
	private int tx;//提醒
	private int status;//存在

	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTx() {
		return tx;
	}

	public void setTx(int tx) {
		this.tx = tx;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getIsPay() {
		return isPay;
	}

	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}

	public int getIsDeliver() {
		return isDeliver;
	}

	public void setIsDeliver(int isDeliver) {
		this.isDeliver = isDeliver;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", user=" + user + ", submitTime=" + submitTime + ", totalPrice="
				+ totalPrice + ", isPay=" + isPay + ", isDeliver=" + isDeliver + ", label=" + label + ", tx=" + tx
				+ ", status=" + status + "]";
	}

	

	

}
