package com.zmm.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.zmm.dto.Screen;

import com.zmm.service.ScreenService;

/**
 * Servlet implementation class ScreenAction
 */
@WebServlet("/ScreenAction")
public class ScreenAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ScreenAction() {
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
		if (operate != null && operate.equals("list")) {
			list(request, response);
		} else if (operate != null && operate.equals("add")) {
			add(request, response);
		} else if (operate != null && operate.equals("delete")) {
			delete(request, response);
		} else if (operate != null && operate.equals("modify")) {
			modify(request, response);
		}

	}

	private void modify(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		ScreenService ss = null;
		Screen s = null;
		StringBuffer sbf = null;
		try {
			ss = new ScreenService();
			s = ss.getScreen(id);
			sbf = new StringBuffer();
			if (name == null || name.equals("")) {
				sbf.append("屏幕尺寸不能为空！");
			} else {
				s.setScreen_name(name);
			}
			if (s != null) {
				if (ss.modifyScreen(s)) {
					request.setAttribute("msg", "修改成功！");
					list(request, response);

				} else {
					request.setAttribute("msg", "修改失败！");
					list(request, response);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		Screen s = null;
		ScreenService ss = null;
		try {
			ss = new ScreenService();
			s = ss.getScreen(id);
			if (s != null) {
				if (ss.deleteScreen(id)) {
					request.setAttribute("msg", "删除成功！");
					list(request, response);
				} else {
					request.setAttribute("msg", "删除失败");
					list(request, response);
				}
			} else {
				request.setAttribute("msg", "找不到");
				list(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ScreenService ss = null;
		String name = request.getParameter("name");
		StringBuffer sbf = null;
		Screen s = null;
		try {
			ss = new ScreenService();
			sbf = new StringBuffer();
			s = new Screen();
			if (name.equals("") || name == null) {
				sbf.append("处理器名不能为空！");
			} else {
				s.setScreen_name(name);
			}
			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				list(request, response);
			} else {
				if (ss.addScreen(s)) {
					list(request, response);
				} else {
					request.setAttribute("msg", " 添加失败！");
					list(request, response);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<Screen> sList = null;
		ScreenService ss = null;
		try {
			ss = new ScreenService();
			sList = ss.listScreen();
			request.setAttribute("sList", sList);
			request.getRequestDispatcher("category/listScreen.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
