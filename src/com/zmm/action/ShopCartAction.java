package com.zmm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zmm.dto.Computer;
import com.zmm.dto.ShopCart;
import com.zmm.service.ComputerService;
import com.zmm.service.ShopCartService;

/**
 * Servlet implementation class ShopCartAction
 */
@WebServlet("/ShopCartAction")
public class ShopCartAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShopCartAction() {
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
		response.setContentType("text/html;charset=utf-8");
		String operate = request.getParameter("operate");
		if (operate == null || operate.equals("")) {
			return;
		} else {
			if (operate.equals("add")) {
				doAdd(request, response);
			} else if (operate.equals("delete")) {
				doDel(request, response);
			} else if (operate.equals("list")) {
				doList(request, response);
			} else if (operate.equals("clearShopCart")) {
				clearShopCart(request, response);
			} else if (operate.equals("updateCount")) {
				updateCount(request, response);
			}
		}

	}

	private void updateCount(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ShopCartService sc = new ShopCartService();
//		StringBuffer sbf = null;
		ComputerService coms = null;
		Computer com = null;
		int id = Integer.parseInt(request.getParameter("id"));
		int count = Integer.parseInt(request.getParameter("count"));

		try {
			coms = new ComputerService();
//			sbf = new StringBuffer();
			com = coms.get(id);
			int totalCount = com.getNum();
			// if(count<=0){
			//
			// }
			// String c = request.getParameter("count");
			// if (c != null && !c.equals("")) {
			// if (Integer.parseInt(c) <= 0) {
			// count = 1;
			// } else if (Integer.parseInt(c) > totalCount) {
			// count = totalCount;
			// } else {
			// count = Integer.parseInt(c);
			//
			// }
			// if (count == 0) {
			// sbf.append("购买数量为0！不能提交订单！");
			// }
			// if (count > com.getNum()) {
			// sbf.append("大于库存量！不能提交订单！");
			//
			// }
			// if (sbf.length() != 0) {
			// request.setAttribute("msg", sbf.toString());
			// doList(request, response);
			//
			// }

			if (sc.updateCount(count, id, session)) {
				request.setAttribute("count", count);
				request.setAttribute("totalCount", totalCount);
				doList(request, response);
			} else {
				request.setAttribute("msg", "修改失败");
				doList(request, response);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// private void updateCount(HttpServletRequest request, HttpServletResponse
	// response) {
	//
	// // TODO Auto-generated method stub
	// HttpSession session =request.getSession();
	// ShopCartService sc = new ShopCartService();
	// StringBuffer sbf = null;
	// ComputerService coms = null;
	// Computer com = null;
	// try {
	// coms = new ComputerService();
	// sbf = new StringBuffer();
	// int count = Integer.parseInt(request.getParameter("count"));
	// int id = Integer.parseInt(request.getParameter("comId"));
	// com = coms.get(id);
	//
	// if (count == 0) {
	// sbf.append("购买数量为0！不能提交订单！");
	// }
	// if (count > com.getNum()) {
	//
	// sbf.append("大于库存量！不能提交订单！");
	//
	// }
	// if (sbf.length() != 0) {
	// request.setAttribute("msg", sbf.toString());
	// doList(request, response);
	//
	// }
	//
	// if (sc.updateCount(count, id, session)) {
	// doList(request, response);
	// } else {
	// request.setAttribute("msg", "修改失败");
	// doList(request, response);
	// }
	// } catch (Exception e) { // TODO: handle exception }
	//
	// }
	// }

	private void clearShopCart(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ShopCartService sc = new ShopCartService();
		sc.clearShopCart(session);
		try {
			request.getRequestDispatcher("user/ShopCart.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void doList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<ShopCart> ls = null;
		ShopCartService ss = null;
		double totalPrice = 0.0;
		double tp = 0.0;
		ShopCart sc = null;
		int totalCount = 0;// 库存
//		int comId = 0;
		try {
			ss = new ShopCartService();
			if (ss.getShopCart(session) != null) {
				ls = new ArrayList<ShopCart>(ss.getShopCart(session));
				// 计算总价格
				for (int i = 0; i < ls.size(); i++) {
					sc = ls.get(i);
//					comId = sc.getCom().getCom_id();
					totalCount = sc.getCom().getNum();
					totalPrice += sc.getCount() * sc.getCom().getRel_price();
				}
				if (totalPrice >= 5000) {
					tp = totalPrice - 100;
				} else {
					tp = totalPrice;
				}
			}
//			request.setAttribute("comId", comId);
			request.setAttribute("totalCount", totalCount);
			request.setAttribute("totalPrice", tp);
			request.setAttribute("ls", ls);
			request.getRequestDispatcher("user/ShopCart.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void doDel(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			HttpSession session = request.getSession();
			int Id;
			if (request.getParameter("id") != null) {
				Id = Integer.parseInt(request.getParameter("id"));
				ShopCartService shopCartService = new ShopCartService();
				shopCartService.deleteShopCart(session, Id);
			}
			doList(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		StringBuffer sbf = new StringBuffer();
		ShopCartService sc = null;
		try {
			PrintWriter out = response.getWriter();
			sc = new ShopCartService();
		
			sbf = sc.addShopCart(id, session);
			if (sbf.length() != 0) {
				out.print("<script language=javascript>alert('" + sbf.toString()
				+ "');javascript:history.back(-1);</script>");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
