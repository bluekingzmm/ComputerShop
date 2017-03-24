package com.zmm.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zmm.dao.IBrand;
import com.zmm.dao.impl.BrandImpl;
import com.zmm.dto.Brand;
import com.zmm.service.BrandService;

/**
 * Servlet implementation class BrandAction
 */
@WebServlet("/BrandAction")
public class BrandAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IBrand brand = new BrandImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BrandAction() {
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
		if (operate != null && operate.equals("listBrand")) {
			listBrand(request, response);
		} else if (operate != null && operate.equals("addBrand")) {
			addBrand(request, response);
		} else if (operate != null && operate.equals("deleteBrand")) {
			deleteBrand(request, response);
		} else if (operate != null && operate.equals("modifyBrand")) {
			modifyBrand(request, response);
		} 

	}


	private void modifyBrand(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		BrandService bs = null;
		Brand b = null;
		StringBuffer sbf=null;
		try {
			bs=new BrandService();
			b = bs.getBrand(id);
			sbf=new StringBuffer();
			if(name==null||name.equals("")){
				sbf.append("品牌名称不能为空！");
			}else{
				b.setBrand_name(name);
			}
			if (b != null) {
				if (bs.modifyBrand(b)) {
                request.setAttribute("msg", "修改成功！");
                listBrand(request, response);
				}else{
					 request.setAttribute("msg", "修改失败！");
		                listBrand(request, response);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void deleteBrand(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		Brand b = null;

		try {
			b = brand.getBrand(id);
			if (b != null) {
				if (brand.deleteBrand(id)) {
					request.setAttribute("msg", "删除成功！");
					listBrand(request, response);
				} else {
					request.setAttribute("msg", "删除失败");
					listBrand(request, response);
				}
			} else {
				request.setAttribute("msg", "找不到");
				listBrand(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addBrand(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String brand_name = request.getParameter("name");
		StringBuffer sbf = null;
		Brand b = null;
		try {
			sbf = new StringBuffer();
			b = new Brand();
			if (brand_name.equals("") || brand_name == null) {
				sbf.append("品牌名不能为空！");
			} else {
				b.setBrand_name(brand_name);
			}
			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				listBrand(request, response);
			} else {
				if (brand.addBrand(b)) {
					listBrand(request, response);
				} else {
					request.setAttribute("msg", " 添加失败！");
					listBrand(request, response);

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void listBrand(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			List<Brand> brandList = brand.listBrand();
			request.setAttribute("bList", brandList);
			request.getRequestDispatcher("category/listBrand.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
