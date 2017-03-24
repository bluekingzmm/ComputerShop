package com.zmm.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zmm.dto.Computer;
import com.zmm.dto.ShopCart;
import com.zmm.service.ComputerService;
import com.zmm.service.ShopCartService;


/**
 * Servlet implementation class FirstAction
 */
public class FirstAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
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
		String operate = "index";
		
		if (operate.equals("index"))
			doFirst(request, response);
	}

	private void doFirst(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int pageNo = 1;
		int pageSize = 6;
		List<Computer> comList = null;
		String keyWord;
		StringBuffer sbf = null;
		ComputerService coms = null;
		boolean flag = false;
		try {
			sbf = new StringBuffer();
			coms = new ComputerService();

			if (flag) {
				keyWord = "where  status = 1 and";
			} else {
				keyWord = "  where status = 1  ";
			}
			if (sbf.toString().equals("")) {
				comList = coms.list(pageSize, pageNo, keyWord);
			} else {
				comList = coms.list(pageSize, pageNo, keyWord + sbf.toString());
			}
			HttpSession session = request.getSession();
			List<ShopCart> ls = null;
			ShopCartService ss = null;
			double totalPrice = 0.0;
		    double tp=0.0;
				ss = new ShopCartService();
				if (ss.getShopCart(session) != null) {
					ls = new ArrayList<ShopCart>(ss.getShopCart(session));
					// 计算总价格
					for (int i = 0; i < ls.size(); i++) {
						ShopCart sc = ls.get(i);
						totalPrice += sc.getCount() * sc.getCom().getRel_price();
					}
					if(totalPrice>=5000){
						tp=totalPrice-100;
					}else{
						tp=totalPrice;
					}
				}
				request.setAttribute("totalPrice", tp);
				request.setAttribute("ls", ls);
				request.setAttribute("comList", comList);
				request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
