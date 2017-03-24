package com.zmm.action;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.zmm.dto.Cpu;

import com.zmm.service.CpuService;

/**
 * Servlet implementation class computerAction
 */
@WebServlet("/CpuAction")
public class CpuAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CpuAction() {
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
		CpuService cs = null;
		Cpu c = null;
		StringBuffer sbf = null;
		try {
			cs = new CpuService();
			c = cs.getCpu(id);
			sbf = new StringBuffer();
			if (name == null || name.equals("")) {
				sbf.append("处理器名称不能为空！");
			} else {
				c.setCpu_name(name);
			}
			if (c != null) {
				if (cs.modifyCpu(c)) {
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
		Cpu c = null;
		CpuService cs = null;
		try {
			cs = new CpuService();
			c = cs.getCpu(id);
			if (c != null) {
				if (cs.deleteCpu(id)) {
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
		CpuService cs = null;
		String name = request.getParameter("name");
		StringBuffer sbf = null;
		Cpu c = null;
		try {
			cs = new CpuService();
			sbf = new StringBuffer();
			c = new Cpu();
			if (name.equals("") || name == null) {
				sbf.append("处理器名不能为空！");
			} else {
				c.setCpu_name(name);
			}
			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				list(request, response);
			} else {
				if (cs.addCpu(c)) {
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
		List<Cpu> cList = null;
		CpuService cs = null;
		try {
			cs = new CpuService();
			cList = cs.listCpu();
			request.setAttribute("cList", cList);
			request.getRequestDispatcher("category/listCpu.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}