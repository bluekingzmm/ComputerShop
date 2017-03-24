package com.zmm.dao;

import java.sql.Connection;
import java.util.List;

import com.zmm.dto.Order;
import com.zmm.dto.OrderDetail;

public interface IOrder {

	/*
	 * 创建连接
	 */
	public void setConnection(Connection con) throws Exception;

	/*
	 * 通过id获得订单信息
	 */

	public Order getById(Long id) throws Exception;

	/*
	 * 添加订单
	 */

	public boolean addOrder(Order o) throws Exception;

	/*
	 * 添加订单详情
	 */

	
	public boolean addOrderDetail(OrderDetail od) throws Exception;

	/*
	 * 删除订单
	 */

	public boolean deleteOrder(Long orderId) throws Exception;
	/*
	 * 删除订单详情
	 */

	public boolean deleteOrderDetail(Long orderId) throws Exception;

	/*
	 * 修改订单
	 */
	public boolean updateOrder(Order o) throws Exception;

	/*
	 * 通过订单编号得到订单信息
	 */

	public Order OrderById(int userId) throws Exception;
	/*
	 * 订单列表显示
	 */

	public List<Order> listOrder(int pageNo, int pageSize, String keyWord) throws Exception;
	
	/*
	 * 通过用户编获取订单信息
	 */
	public List<Order> listOrderByUserId(int userId) throws Exception;

	/*
	 * 获取订单个数
	 * 
	 */

	public int getCount(String keyWord) throws Exception;

	/*
	 * 订单详情列表
	 */
	public List<OrderDetail> listOrderDetail(Long orderId) throws Exception;
	

	
	/*
	 * 销售排名
	 */
	public List<OrderDetail> sale() throws Exception;
	
	
	
	/*
	 * 显示信息数据
	 */
	public List<Order> listMessge() throws Exception;
	
}
