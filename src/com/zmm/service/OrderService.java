/**
 * 
 */
package com.zmm.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.zmm.dao.IOrder;
import com.zmm.dao.impl.OrderImpl;
import com.zmm.db.DataBase;
import com.zmm.dto.Computer;
import com.zmm.dto.Order;
import com.zmm.dto.OrderDetail;
import com.zmm.dto.ShopCart;

/**
 * @author Administrator
 *
 */
public class OrderService {
	IOrder order = new OrderImpl();

	Connection con = null;

	/*
	 * 通过订单编号得到订单信息 参数：订单编号（Long） 返回值：Order
	 */
	public Order getById(Long id) throws Exception {
		// TODO Auto-generated method stub
		Order o = null;
		try {
			con = DataBase.getConn();
			order.setConnection(con);
			o = order.getById(id);
		} catch (Exception e) {
		} finally {
			DataBase.releaseConnection(con);
		}
		return o;
	}

	/*
	 * 通过用户id得到订单 参数：userId 用户id 返回值：Order
	 */
	public Order OrderById(int userId) throws Exception {
		// TODO Auto-generated method stub
		Order o = null;

		try {
			con = DataBase.getConn();
			order.setConnection(con);
			o = order.OrderById(userId);
		} catch (Exception e) {
		} finally {
			DataBase.releaseConnection(con);
		}
		return o;
	}

	/*
	 * 添加订单信息 参数：Order 对象，List<ShopCart>,userId 无返回值
	 */
	public void addOrder(List<ShopCart> sList, Order o, int userId) throws Exception {
		try {

			con = DataBase.getConn();
			order.setConnection(con);
			// 订单编号获得的是自1970-1-01 00:00:00.000 到当前时刻的时间距离+用户id
			Long orderId = System.currentTimeMillis();
			orderId=Long.parseLong(orderId.toString()+userId);
			o.setOrderId(orderId);
			order.addOrder(o);
			List<OrderDetail> lod = new ArrayList<OrderDetail>();
			// 添加订单详情
			for (int i = 0; i < sList.size(); i++) {
				ShopCart sc = sList.get(i);
				OrderDetail od = new OrderDetail();
				od.setAccount(sc.getCount());
				od.setBuy_price(sc.getCom().getRel_price());
				Computer com = new Computer();
				com.setCom_id(sc.getCom().getCom_id());
				com.setModer(sc.getCom().getModel());
				od.setCom(com);
				od.setOrder_id(orderId);
				lod.add(od);
				order.addOrderDetail(od);// 放在for里面，否则只是添加一条数据
			}

		} catch (Exception e) {
		} finally {
			DataBase.releaseConnection(con);
		}

	}
	
	
	
	/*
	 * 添加订单单个信息 参数：Order 对象，List<ShopCart>,userId 无返回值
	 */
	public void addOneOrder(ShopCart sc, Order o, int userId) throws Exception {
		try {

			con = DataBase.getConn();
			order.setConnection(con);
			// 订单编号获得的是自1970-1-01 00:00:00.000 到当前时刻的时间距离+用户id
			Long orderId = System.currentTimeMillis();
			orderId=Long.parseLong(orderId.toString()+userId);
			o.setOrderId(orderId);
			order.addOrder(o);
			
//			List<OrderDetail> lod = new ArrayList<OrderDetail>();
			// 添加订单详情
//			for (int i = 0; i < sList.size(); i++) {
//				ShopCart sc = sList.get(i);
				OrderDetail od = new OrderDetail();
				od.setAccount(sc.getCount());
				od.setBuy_price(sc.getCom().getRel_price());
				Computer com = new Computer();
				com.setCom_id(sc.getCom().getCom_id());
				com.setModer(sc.getCom().getModel());
				od.setCom(com);
				od.setOrder_id(orderId);
//				lod.add(od);
				order.addOrderDetail(od);// 放在for里面，否则只是添加一条数据
//			}

		} catch (Exception e) {
		} finally {
			DataBase.releaseConnection(con);
		}

	}

	/*
	 * 删除订单信息 参数：Order 对象 返回值boolean
	 */
	public boolean deleteOrder(Long orderId) throws Exception {
		boolean result = false;
		try {
			con = DataBase.getConn();
			order.setConnection(con);
			result = order.deleteOrder(orderId);
			result = order.deleteOrderDetail(orderId);
		} catch (Exception e) {
		} finally {
			DataBase.releaseConnection(con);
		}
		return result;
	}

	/*
	 * 修改订单信息 参数：Order 对象 返回值boolean
	 */
	public boolean updateOrder(Order o) throws Exception {
		boolean result = false;
		try {
			con = DataBase.getConn();
			order.setConnection(con);
			result = order.updateOrder(o);
		} catch (Exception e) {
		} finally {
			DataBase.releaseConnection(con);
		}
		return result;
	}

	/*
	 * 显示订单信息 参数：pageNo当前页的页号，pageSize每页显示的个数，Keyword关键字查询 返回值：List
	 */
	public List<Order> listOrder(int pageNo, int pageSize, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		List<Order> oList = null;
		try {
			con = DataBase.getConn();
			order.setConnection(con);
			oList = order.listOrder(pageNo, pageSize, keyWord);
		} catch (Exception e) {
		} finally {
			DataBase.releaseConnection(con);
		}
		return oList;
	}

	/*
	 * 通过用户编号获得订单信息 参数：用户编号 返回值：List
	 */

	public List<Order> listOrderByUserId(int userId) throws Exception {
		// TODO Auto-generated method stub
		List<Order> oList = null;
		try {
			con = DataBase.getConn();
			order.setConnection(con);
			oList = order.listOrderByUserId(userId);
		} catch (Exception e) {
		} finally {
			DataBase.releaseConnection(con);
		}
		return oList;
	}

	public List<Order> listMessge() throws Exception {
		// TODO Auto-generated method stub
		List<Order> oList = null;
		try {
			con = DataBase.getConn();
			order.setConnection(con);
			oList = order.listMessge();
		} catch (Exception e) {
		} finally {
			DataBase.releaseConnection(con);
		}
		return oList;
	}

	/*
	 * 通过订单编号显示详情信息 参数：订单编号（Long） 返回值：List类型
	 */
	public List<OrderDetail> listOrderDetail(Long orderId) throws Exception {
		// TODO Auto-generated method stub
		List<OrderDetail> oList = null;
		try {
			con = DataBase.getConn();
			order.setConnection(con);
			oList = order.listOrderDetail(orderId);
		} catch (Exception e) {
		} finally {
			DataBase.releaseConnection(con);
		}
		return oList;
	}

	/*
	 * 获得订单总个数 返回int 类型 参数：关键字keyWord
	 */
	public int getCount(String keyWord) throws Exception {
		// TODO Auto-generated method stub
		int count = 0;
		try {
			con = DataBase.getConn();
			order.setConnection(con);
			count = order.getCount(keyWord);
		} catch (Exception e) {
		} finally {
			DataBase.releaseConnection(con);
		}

		return count;
	}

	/*
	 * 显示商品销售情况 List 订单详情 返回List类型
	 */
	public List<OrderDetail> sale() throws Exception {
		// TODO Auto-generated method stub

		List<OrderDetail> cList = null;

		try {
			con = DataBase.getConn();
			order.setConnection(con);
			cList = order.sale();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return cList;
	}

}
