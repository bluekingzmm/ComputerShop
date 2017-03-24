package com.zmm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zmm.dao.IOrder;
import com.zmm.db.DataBase;
import com.zmm.dto.Brand;
import com.zmm.dto.Computer;
import com.zmm.dto.Order;
import com.zmm.dto.OrderDetail;
import com.zmm.dto.User;

public class OrderImpl implements IOrder {

	Connection con = null;

	public void setConnection(Connection con) throws Exception {
		// TODO Auto-generated method stub
		this.con = con;
	}

	/*
	 * 通过订单编号得到订单信息 参数：订单编号（Long） 返回值：Order
	 */
	public Order getById(Long id) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Order o = null;
		try {
			ps = con.prepareStatement(" select * from vorder where order_id=? ");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				o = new Order();
				o.setIsDeliver(rs.getInt("isdeliver"));
				o.setIsPay(rs.getInt("ispay"));
				o.setLabel(rs.getString("label"));
				o.setTotalPrice(rs.getDouble("total_price"));
				o.setSubmitTime(rs.getString("buy_date"));
				o.setOrderId(rs.getLong("order_id"));
				o.setTx(rs.getInt("tx"));
				o.setStatus(rs.getInt("state"));
				User user = new User();

				user.setUser_name(rs.getString("user_name"));
				user.setUser_id(rs.getInt("user_id"));
				o.setUser(user);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
		}

		return o;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IOrder#addOrder(com.zmm.dto.Order) 添加订单信息 参数Order o
	 * 返回值：boolean
	 */
	public boolean addOrder(Order o) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(
					"insert into order_info (order_id,total_price,user_id,buy_date,ispay,isdeliver) values(?,?,?,?,?,?)");
			ps.setLong(1, o.getOrderId());
			ps.setDouble(2, o.getTotalPrice());
			ps.setInt(3, o.getUser().getUser_id());
			ps.setString(4, o.getSubmitTime());
			ps.setInt(5, o.getIsPay());
			ps.setInt(6, o.getIsDeliver());
			if (ps.executeUpdate() > 0) {
				DataBase.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
		} finally {
			ps.close();
		}
		return false;
	}

	/*
	 * 删除订单信息 参数：Order 对象 返回值boolean
	 */
	public boolean deleteOrder(Long orderId) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from order_info where order_id=?");
			ps.setLong(1, orderId);
			if (ps.executeUpdate() > 0) {
				DataBase.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();

			DataBase.rollback();
		} finally {
			ps.close();
		}
		return false;
	}

	/*
	 * 修改订单信息（发货，支付） 参数：Order 对象 返回值boolean
	 */
	public boolean updateOrder(Order o) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("update order_info set ispay=?,isdeliver=?,label=?,tx=?,state=? where order_id=?");
			ps.setInt(1, o.getIsPay());
			ps.setInt(2, o.getIsDeliver());
			ps.setString(3, o.getLabel());
			ps.setInt(4, o.getTx());
			ps.setInt(5, o.getStatus());
			ps.setLong(6, o.getOrderId());
			if (ps.executeUpdate() > 0) {
				DataBase.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();

			DataBase.rollback();
		} finally {
			ps.close();
		}
		return false;
	}

	/*
	 * 显示订单信息 参数：pageNo当前页的页号，pageSize每页显示的个数，Keyword关键字查询 返回值：List
	 */
	public List<Order> listOrder(int pageNo, int pageSize, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Order> oList = null;
		Order o = null;
		try {
			ps = con.prepareStatement("select * from vorder " + keyWord + " limit ?,?");
			ps.setInt(1, (pageNo - 1) * pageSize);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			oList = new ArrayList<Order>();
			while (rs.next()) {
				o = new Order();
				o.setIsDeliver(rs.getInt("isdeliver"));
				o.setIsPay(rs.getInt("ispay"));
				o.setLabel(rs.getString("label"));
				o.setTotalPrice(rs.getDouble("total_price"));
				o.setSubmitTime(rs.getString("buy_date"));
				o.setOrderId(rs.getLong("order_id"));
				o.setTx(rs.getInt("tx"));
				o.setStatus(rs.getInt("state"));

				User user = new User();
				user.setUser_name(rs.getString("user_name"));
				user.setUser_id(rs.getInt("user_id"));
				o.setUser(user);
				oList.add(o);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
		}

		return oList;
	}

	/*
	 * 获得订单总个数 返回int 类型 参数：关键字keyWord
	 */
	public int getCount(String keyWord) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			ps = con.prepareStatement("select count(*) from vorder" + " " + keyWord);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
				return count;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
		}

		return count;
	}

	/*
	 * 通过订单编号显示详情信息 参数：订单编号（Long） 返回值：List类型
	 */
	public List<OrderDetail> listOrderDetail(Long orderId) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		OrderDetail od = null;
		List<OrderDetail> oList = null;
		try {
			ps = con.prepareStatement("select * from vorderdetail where order_id=? ");
			ps.setLong(1, orderId);
			oList = new ArrayList<OrderDetail>();
			rs = ps.executeQuery();
			while (rs.next()) {
				od = new OrderDetail();
				od.setAccount(rs.getInt("account"));
				od.setBuy_price(rs.getDouble("buy_price"));
				od.setOrder_detail_id(rs.getInt("order_detail_id"));
				Computer com = new Computer();
				Brand brand = new Brand();
				brand.setBrand_name(rs.getString("brand_name"));
				com.setCom_id(rs.getInt("com_id"));
				com.setImgPath(rs.getString("img_path"));
				com.setModer(rs.getString("model"));
				com.setBrand(brand);
				od.setCom(com);
				od.setOrder_id(rs.getLong("order_id"));
				oList.add(od);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oList;
	}

	/*
	 * 通过用户id得到订单 参数：userId 用户id 返回值：Order
	 */
	public Order OrderById(int userId) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Order o = null;
		try {
			ps = con.prepareStatement("select * from vorder where user_id=?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				o = new Order();
				o.setIsDeliver(rs.getInt("isdeliver"));
				o.setIsPay(rs.getInt("ispay"));
				o.setLabel(rs.getString("label"));
				o.setTotalPrice(rs.getDouble("total_price"));
				o.setSubmitTime(rs.getString("buy_date"));
				o.setOrderId(rs.getLong("order_id"));
				o.setTx(rs.getInt("tx"));
				o.setStatus(rs.getInt("state"));

				User user = new User();
				user.setUser_name(rs.getString("user_name"));
				user.setUser_id(rs.getInt("user_id"));
				o.setUser(user);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
		}

		return o;
	}

	// 通过用户id显示订单
	public List<Order> listOrderByUserId(int userId) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Order> oList = null;
		Order o = null;
		try {
			ps = con.prepareStatement("select * from vorder where user_id=?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			oList = new ArrayList<Order>();
			while (rs.next()) {
				o = new Order();
				o.setIsDeliver(rs.getInt("isdeliver"));
				o.setIsPay(rs.getInt("ispay"));
				o.setLabel(rs.getString("label"));
				o.setTotalPrice(rs.getDouble("total_price"));
				o.setSubmitTime(rs.getString("buy_date"));
				o.setOrderId(rs.getLong("order_id"));
				o.setTx(rs.getInt("tx"));
				o.setStatus(rs.getInt("state"));

				User user = new User();
				user.setUser_name(rs.getString("user_name"));
				user.setUser_id(rs.getInt("user_id"));
				o.setUser(user);
				oList.add(o);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
		}

		return oList;
	}

	// 增加订单详情
	public boolean addOrderDetail(OrderDetail od) throws Exception {

		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(
					"insert into order_detail_info(order_id,com_id,model,buy_price,account) values(?,?,?,?,?) ");
			ps.setLong(1, od.getOrder_id());
			ps.setInt(2, od.getCom().getCom_id());
			ps.setString(3, od.getCom().getModel());
			ps.setDouble(4, od.getBuy_price());
			ps.setInt(5, od.getAccount());
			if (ps.executeUpdate() > 0) {
				DataBase.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
		} finally {
			ps.close();

		}

		return false;
	}

	// 删除订单详情
	public boolean deleteOrderDetail(Long orderId) throws Exception {
		PreparedStatement ps = null;
		try {

			ps = con.prepareStatement("delete from order_detail_info where order_id=?");
			ps.setLong(1, orderId);
			if (ps.executeUpdate() > 0) {
				DataBase.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataBase.rollback();
		} finally {
			ps.close();
		}
		return false;
	}

	/*
	 * 显示商品销售情况 List 订单详情 返回List类型
	 */
	public List<OrderDetail> sale() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		OrderDetail od = null;
		List<OrderDetail> oList = null;
		try {
			ps = con.prepareStatement("select * from vsale ");
			oList = new ArrayList<OrderDetail>();
			rs = ps.executeQuery();
			while (rs.next()) {
				od = new OrderDetail();
				od.setAccount(rs.getInt("count"));
				od.setBuy_price(rs.getDouble("rel_price"));
				Computer com = new Computer();
				Brand brand = new Brand();
				brand.setBrand_name(rs.getString("brand_name"));
				com.setCom_id(rs.getInt("com_id"));
				com.setModer(rs.getString("model"));
				com.setBrand(brand);
				od.setCom(com);
				oList.add(od);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oList;
	}

	
	
	/*
	 * 显示信息
	 */
	public List<Order> listMessge() throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Order> oList = null;
		Order o = null;
		try {
			ps = con.prepareStatement("select * from vorder where state=1 ");
			rs = ps.executeQuery();
			oList = new ArrayList<Order>();
			while (rs.next()) {
				o = new Order();
				o.setIsDeliver(rs.getInt("isdeliver"));
				o.setIsPay(rs.getInt("ispay"));
				o.setLabel(rs.getString("label"));
				o.setTotalPrice(rs.getDouble("total_price"));
				o.setSubmitTime(rs.getString("buy_date"));
				o.setOrderId(rs.getLong("order_id"));
				o.setTx(rs.getInt("tx"));
				o.setStatus(rs.getInt("state"));
				User user = new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_name(rs.getString("user_name"));
				o.setUser(user);
				oList.add(o);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
		}

		return oList;
	}
}
