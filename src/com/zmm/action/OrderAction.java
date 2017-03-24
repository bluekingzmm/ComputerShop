package com.zmm.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.zmm.dto.Order;
import com.zmm.dto.OrderDetail;
import com.zmm.dto.ShopCart;
import com.zmm.dto.User;
import com.zmm.service.ComputerService;
import com.zmm.service.OrderService;
import com.zmm.service.ShopCartService;
import com.zmm.service.UserService;

/**
 * Servlet implementation class OrderAction
 */
@WebServlet("/OrderAction")
public class OrderAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=utf-8");
		String operate = request.getParameter("operate");
		if (operate == null || operate.equals("")) {
			return;
		} else {
			if (operate.equals("list")) {
				list(request, response);
			} else if (operate.equals("add")) {
				add(request, response);
			} else if (operate.equals("updatePay")) {
				updatePay(request, response);
			} else if (operate.equals("updateDeliver")) {
				updateDeliver(request, response);
			} else if (operate.equals("show")) {
				show(request, response);
			} else if (operate.equals("listOrder")) {
				listOrder(request, response);
			} else if (operate.equals("sale")) {
				doSale(request, response);
			} else if (operate.equals("deleteOrder")) {
				deleteOrder(request, response);
			} else if (operate.equals("Echarts")) {
				Echarts(request, response);
			} else if (operate.equals("updateService")) {
				updateService(request, response);
			} else if (operate.equals("listMessge")) {
				listMessge(request, response);
			} else if (operate.equals("deleteMessge")) {
				deleteMessge(request, response);
			} else if (operate.equals("addOne")) {
				addOne(request, response);
			}
		}

	}

	/*
	 * 修改信息状态
	 */
	private void deleteMessge(HttpServletRequest request, HttpServletResponse response) {
		Long id = Long.parseLong(request.getParameter("id"));
		OrderService os = null;
		Order o = null;
		try {
			os = new OrderService();
			o = os.getById(id);
			o.setStatus(0);
			o.setLabel(null);
			if (os.updateOrder(o)) {
				listMessge(request, response);
			} else {
				listMessge(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * 显示信息
	 */
	private void listMessge(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<Order> ol = null;
		try {
			OrderService os = new OrderService();
			ol = os.listMessge();
			request.setAttribute("oList", ol);
			request.getRequestDispatcher("admin/listMessge.jsp").forward(request, response);

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * set GLOBAL event_scheduler = ON; 开启定时器 订单备注，提醒,mysql定时器10秒将tx设为0 create
	 * event upload_to_sdmp on schedule every 10 second starts timestamp
	 * '2016-1-01 10:00:00' #on schedule every 1 SECOND do update order_info set
	 * tx=0
	 * 
	 * 
	 */
	private void updateService(HttpServletRequest request, HttpServletResponse response) {
		Long id = Long.parseLong(request.getParameter("id"));
		String label = request.getParameter("label");
		OrderService os = null;
		Order o = null;
		try {
			os = new OrderService();
			o = os.getById(id);
			o.setTx(1);
			o.setLabel(label);
			o.setStatus(1);
			if (os.updateOrder(o)) {
				listOrder(request, response);
			} else {
				listOrder(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/*
	 * 图表
	 */
	private void Echarts(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.getRequestDispatcher("admin/Echarts.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}

	/*
	 * 删除订单（后台删除订单）
	 */
	private void deleteOrder(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id"));
		OrderService os = null;
		try {
			os = new OrderService();
			os.deleteOrder(id);
			list(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}

	/*
	 * 销售显示
	 */
	private void doSale(HttpServletRequest request, HttpServletResponse response) {
		List<OrderDetail> oList = null;
		OrderService os = new OrderService();
		try {
			oList = os.sale();
			request.setAttribute("oList", oList);
			request.getRequestDispatcher("admin/sale.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * 前台订单
	 */

	private void listOrder(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User u = null;
		List<Order> ol = null;
		try {
			OrderService os = new OrderService();
			u = (User) session.getAttribute("userInfo");
			ol = os.listOrderByUserId(u.getUser_id());
			request.setAttribute("ol", ol);
			request.getRequestDispatcher("user/listOrder.jsp").forward(request, response);

		} catch (

		Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

	}

	/*
	 * 订单详情
	 */
	private void show(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id"));
		OrderService os = null;
		Order o = null;
		List<OrderDetail> oList = null;
		try {
			os = new OrderService();
			o = os.getById(id);
			oList = os.listOrderDetail(id);
			request.setAttribute("ol", oList);
			request.setAttribute("od", o);
			request.getRequestDispatcher("admin/listDetailOrder.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}

	/*
	 * 修改发货状态
	 */
	private void updateDeliver(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id"));
		OrderService os = null;
		Order o = null;
		try {
			os = new OrderService();
			o = os.getById(id);
			o.setIsDeliver(1);
			if (os.updateOrder(o)) {
				list(request, response);
			} else {
				list(request, response);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}

	/*
	 * 修改支付状态
	 */
	private void updatePay(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id"));
		OrderService os = null;
		Order o = null;
		try {
			os = new OrderService();
			o = os.getById(id);
			o.setIsPay(1);

			if (os.updateOrder(o)) {
				listOrder(request, response);
			} else {
				listOrder(request, response);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

	}

	/*
	 * 添加订单（全部结算）
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserService us = new UserService();
		OrderService os = new OrderService();
		ShopCartService scs = null;
		ShopCart s = null;
		User u = (User) session.getAttribute("userInfo");
		// int comId = Integer.parseInt(request.getParameter("comId"));
		// System.out.println(comId);
		StringBuffer sbf = null;
		ComputerService cs = null;
		try {
			cs = new ComputerService();
			sbf = new StringBuffer();
			scs = new ShopCartService();
			List<ShopCart> sList = new ArrayList<ShopCart>(scs.getShopCart(session));

			Order o = new Order();
			// 获得用户
			User user = us.getById(u.getUser_id());
			o.setUser(user);
			int userId = (Integer) session.getAttribute("userId");// 用户登录时存的session

			// // 设置订单编号
			// Long orderId = System.currentTimeMillis();
			// orderId = Long.parseLong(orderId.toString() + u.getUser_id());

			// o.setOrderId(orderId);
			// 设置支付与发货状态为0
			o.setIsDeliver(0);
			o.setIsPay(0);
			double totalPrice = 0.0;
			for (int i = 0; i < sList.size(); i++) {
				s = sList.get(i);
				totalPrice += s.getCom().getRel_price() * s.getCount();

			}
			// 拿到总价格
			if (totalPrice == 0) {
				sbf.append("总价不能为空");
			} else if (totalPrice >= 5000) {
				o.setTotalPrice(totalPrice - 100);// 优惠100元

			} else {
				o.setTotalPrice(totalPrice);

			}

			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("ShopCartAction?operate=list").forward(request, response);

			}
			// 设置提交订单时间
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			Date date = new Date();
			String submitTime = dateFormat.format(date);
			o.setSubmitTime(submitTime);
			cs.updateSubCount(session);
			os.addOrder(sList, o, userId);
			scs.clearShopCart(session);
			listOrder(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

	}

	/*
	 * 添加订单（单个）
	 */
	private void addOne(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserService us = new UserService();
		OrderService os = new OrderService();
		ShopCartService scs = null;
		User u = (User) session.getAttribute("userInfo");
		int comId = Integer.parseInt(request.getParameter("id"));
		String tPrice = request.getParameter("tPrice");
		// System.out.println(comId);
		StringBuffer sbf = null;
		ComputerService cs = null;
		try {
			cs = new ComputerService();
			sbf = new StringBuffer();
			scs = new ShopCartService();
			ShopCart sc = scs.getById(session, comId);//获得对象
			Order o = new Order();
			// 获得用户
			User user = us.getById(u.getUser_id());
			o.setUser(user);
			int userId = (Integer) session.getAttribute("userId");// 用户登录时存的session
			// 设置支付与发货状态为0
			o.setIsDeliver(0);
			o.setIsPay(0);
			double totalPrice = 0.0;
			totalPrice = Double.parseDouble(tPrice);
			// 拿到总价格
			o.setTotalPrice(totalPrice);

			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("ShopCartAction?operate=list").forward(request, response);

			}
			// 设置提交订单时间
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			Date date = new Date();
			String submitTime = dateFormat.format(date);
			o.setSubmitTime(submitTime);
			cs.updateSubCount(session);
			os.addOneOrder(sc, o, userId);
			// 删除购物车	
			scs.deleteShopCart(session, comId);
			listOrder(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

	}
	/*
	 * 后台订单
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int pageNo = 1, pageSize = 3, count = 0;
		String pn = request.getParameter("pageNo");
		String ispay = request.getParameter("isPay");
		String orderId = request.getParameter("orderId");
		String userName = request.getParameter("userName");
		String isDeliver = request.getParameter("isDeliver");
		boolean flag = false;
		String keyWord;
		StringBuffer sbf = null;
		List<Order> oList = null;
		try {
			sbf = new StringBuffer();
			OrderService os = new OrderService();
			if (pn != null && !pn.equals(""))
				pageNo = Integer.parseInt(pn);

			if (pageNo < 1)
				pageNo = 1;

			if (ispay != null && !ispay.equals("")) {

				sbf.append(" ispay =' ");
				sbf.append(ispay);
				sbf.append(" '");
				flag = true;
			}
			if (isDeliver != null && !isDeliver.equals("")) {

				sbf.append(" isdeliver =' ");
				sbf.append(isDeliver);
				sbf.append(" '");
				flag = true;
			}
			if (orderId != null && !orderId.equals("")) {
				sbf.append(" order_id like '%");
				sbf.append(orderId);
				sbf.append("%'");
				flag = true;
			}
			if (userName != null && !userName.equals("")) {
				sbf.append(" user_name like '%");
				sbf.append(userName);
				sbf.append("%'");
				flag = true;
			}

			if (flag) {
				keyWord = "where";

			} else {
				keyWord = "";
			}
			if (sbf.toString().equals("")) {
				oList = os.listOrder(pageNo, pageSize, keyWord);
				count = os.getCount(keyWord);
			} else {
				oList = os.listOrder(pageNo, pageSize, keyWord + sbf.toString());
				count = os.getCount(keyWord + sbf.toString());

			}
			request.setAttribute("oList", oList);
			int totalPage = (int) Math.ceil((double) count / pageSize);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("count", count);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalPage", totalPage);
			request.getRequestDispatcher("admin/listOrder.jsp").forward(request, response);

		} catch (

		Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

	}

}
