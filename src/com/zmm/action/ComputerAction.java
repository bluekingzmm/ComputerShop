package com.zmm.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zmm.dto.Brand;
import com.zmm.dto.Computer;
import com.zmm.dto.Cpu;
import com.zmm.dto.Screen;
import com.zmm.service.BrandService;
import com.zmm.service.ComputerService;
import com.zmm.service.CpuService;
import com.zmm.service.ScreenService;


@WebServlet("/ComputerAction")
public class ComputerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ComputerAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		try {
			String operate = request.getParameter("operate");
			if (operate == null || operate.equals("")) {
				return;
			} else {
				if (operate.equals("create")) {
					create(request, response);
				} else if (operate.equals("add")) {
					add(request, response);
				} else if (operate.equals("list")) {
					list(request, response);
				} else if (operate.equals("update")) {
					update(request, response);
				} else if (operate.equals("show")) {
					show(request, response);
				} else if (operate.equals("edit")) {
					edit(request, response);
				} else if (operate.equals("changeState")) {
					changeState(request, response);
				} else if (operate.equals("getProduct")) {
					getProduct(request, response);
				} else if (operate.equals("listCom")) {
					listCom(request, response);
			}
			}

		} catch (Exception e) {
			e.printStackTrace();
//			response.sendRedirect(request.getContextPath() + "errors/404.jsp");
		}
	}

	

	private void listCom(HttpServletRequest request, HttpServletResponse response) {
		int pageNo = 1;
		int pageSize = 6;
		int count = 0;
		List<Computer> comList = null;
		String keyWord;
		List<Brand> bList = null;
		List<Screen> sList = null;
		List<Cpu> cList = null;
		StringBuffer sbf = null;
		ScreenService ss = null;
		CpuService cs = null;
		BrandService bs = null;
		ComputerService coms = null;
		boolean flag = false;
		String pn = request.getParameter("pageNo");
		String screenName = request.getParameter("screen");
		String brandName = request.getParameter("brandName");
		String colorName = request.getParameter("color");
		String cpuName = request.getParameter("cpuName");
		String fPrice = request.getParameter("fPrice");
		String sPrice = request.getParameter("sPrice");
		try {
			sbf = new StringBuffer();
			coms = new ComputerService();
			if (pn != null && !pn.equals(""))
				pageNo = Integer.parseInt(pn);

			if (brandName != null && !brandName.equals("")) {
				if (flag)
					sbf.append(" and ");
				sbf.append(" brand_name = '");
				sbf.append(brandName);
				sbf.append(" '");
				flag = true;
			}
			if (cpuName != null && !cpuName.equals("")) {
				if (flag)
					sbf.append(" and ");
				sbf.append(" cpu_name = '");
				sbf.append(cpuName);
				sbf.append(" '");
				flag = true;
			}
			if (screenName != null && !screenName.equals("")) {
				if (flag)
					sbf.append(" and ");
				sbf.append(" screen_name = '");
				sbf.append(screenName);
				sbf.append("英寸'");
				flag = true;
			}
			if (colorName != null && !colorName.equals("")) {
				if (flag)
					sbf.append(" and ");
				sbf.append(" color like '%");
				sbf.append(colorName);
				sbf.append("%' ");
				flag = true;
			}
			if (fPrice != null && !fPrice.equals("") || sPrice != null && !sPrice.equals("")) {
				if (flag)
					sbf.append(" and ");
				sbf.append(" rel_price BETWEEN ' ");
				sbf.append(fPrice);
				sbf.append(" ' and '");
				sbf.append(sPrice);
				sbf.append(" '");
				flag = true;
			}
			if (request.getParameter("search") != null && !request.getParameter("search").equals("")) {
				if (flag)
					sbf.append(" and ");
				sbf.append(" brand_name like '%");
				sbf.append(request.getParameter("search"));
				sbf.append("%' ");
				sbf.append(" or ");
				sbf.append(" model like '%");
				sbf.append(request.getParameter("search"));
				sbf.append("%' ");
				sbf.append(" or ");
				sbf.append(" pixels like '%");
				sbf.append(request.getParameter("search"));
				sbf.append("%' ");
				sbf.append(" or ");
				sbf.append(" ram like '%");
				sbf.append(request.getParameter("search"));
				sbf.append("%' ");
				sbf.append(" or ");
				sbf.append(" screen_name like '%");
				sbf.append(request.getParameter("search"));
				sbf.append("%' ");
				sbf.append(" or ");
				sbf.append(" cpu_name like '%");
				sbf.append(request.getParameter("search"));
				sbf.append("%' ");
				sbf.append(" or ");
				sbf.append(" color like '%");
				sbf.append(request.getParameter("search"));
				sbf.append("%' ");
				flag = true;
			}
			if (flag) {
				keyWord = " where status = 1 and ";
			} else {
				keyWord = " where status = 1  ";
			}
			if (sbf.toString().equals("")) {
				comList = coms.list(pageSize, pageNo, keyWord);
				count = coms.getCount(keyWord);
			} else {
				comList = coms.list(pageSize, pageNo, keyWord + sbf.toString());
				count = coms.getCount(keyWord + sbf.toString());
			}
			int totalPage = (int) Math.ceil((double) count / pageSize);

			request.setAttribute("comList", comList);
			cs = new CpuService();
			cList = cs.listCpu();
			request.setAttribute("cList", cList);

			bs = new BrandService();
			bList = bs.listBrand();
			request.setAttribute("bList", bList);

			ss = new ScreenService();
			sList = ss.listScreen();

			request.setAttribute("sList", sList);

			request.setAttribute("count", count);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalPage", totalPage);

			request.getRequestDispatcher("list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getProduct(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int com_id = Integer.parseInt(request.getParameter("id"));
		Computer com = null;
		ComputerService coms = null;
		try {
			coms = new ComputerService();
			com = coms.get(com_id);
			request.setAttribute("com", com);
			request.getRequestDispatcher("product.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void show(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		ComputerService coms = null;
		Computer com = null;
		try {
			coms = new ComputerService();
			com = coms.get(id);
			request.setAttribute("com", com);
			request.getRequestDispatcher("computer/show.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Screen> sList = null;
		List<Brand> bList = null;
		List<Cpu> cList = null;
		CpuService cs = null;
		BrandService bs = null;
		ScreenService ss = null;
		Computer com = null;
		ComputerService coms = null;
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			coms = new ComputerService();
			com = coms.get(id);
			cs = new CpuService();
			bs = new BrandService();
			ss = new ScreenService();
			sList = ss.listScreen();
			cList = cs.listCpu();
			bList = bs.listBrand();
			request.setAttribute("cList", cList);
			request.setAttribute("bList", bList);
			request.setAttribute("sList", sList);
			request.setAttribute("com", com);
			request.getRequestDispatcher("computer/update.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Screen> sList = null;
		List<Brand> bList = null;
		List<Cpu> cList = null;
		CpuService cs = null;
		BrandService bs = null;
		ScreenService ss = null;
		try {
			cs = new CpuService();
			bs = new BrandService();
			ss = new ScreenService();
			sList = ss.listScreen();
			cList = cs.listCpu();
			bList = bs.listBrand();
			request.setAttribute("cList", cList);
			request.setAttribute("bList", bList);
			request.setAttribute("sList", sList);
			request.getRequestDispatcher("computer/add.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StringBuffer sbf = null;
		Cpu cpu = null;
		Screen screen = null;
		Brand brand = null;
		Computer com = null;
		ComputerService coms = null;
		ScreenService ss = null;
		BrandService bs = null;
		CpuService cs = null;
		int id = Integer.parseInt(request.getParameter("id"));
		try {

			cs = new CpuService();
			bs = new BrandService();
			ss = new ScreenService();
			coms = new ComputerService();
			com = coms.get(id);
			if (com != null) {
				sbf = new StringBuffer();
				String brandId = request.getParameter("brandName");
				if (brandId == null || brandId.equals("")) {
					sbf.append("品牌名称不能为空！");
				} else {
					int bi = Integer.parseInt(brandId);
					brand = bs.getBrand(bi);
					com.setBrand(brand);
				}

				String model = request.getParameter("model");
				if (model.equals("") || model == null) {
					sbf.append("电脑型号不能为空！");
				} else {

					com.setModer(model);
				}
				String cpuId = request.getParameter("cpuName");
				if (cpuId == null || cpuId.equals("")) {
					sbf.append("处理器不能为空！");
				} else {
					int ci = Integer.parseInt(cpuId);
					cpu = cs.getCpu(ci);
					com.setCpu(cpu);
				}
				String screenId = request.getParameter("screenName");
				if (screenId == null || screenId.equals("")) {
					sbf.append("屏幕尺寸不能为空！");
				} else {
					int si = Integer.parseInt(screenId);
					screen = ss.getScreen(si);
					com.setScreen(screen);
				}
				String color = request.getParameter("color");
				if (color == null || color.equals("")) {
					sbf.append("颜色不能为空！");
				} else {
					com.setColor(color);
				}
				String price = request.getParameter("price");
				if (price == null || price.equals("")) {
					sbf.append("价格不能为空！");
				} else {
					double p = Double.parseDouble(price);
					com.setPrice(p);
				}
				String realPrice = request.getParameter("relPrice");
				if (realPrice == null || realPrice.equals("")) {
					sbf.append("售价不能为空！");
				} else {
					double p = Double.parseDouble(realPrice);
					com.setRel_price(p);
				}
				String ram = request.getParameter("ram");
				if (ram == null || ram.equals("")) {
					sbf.append("内存不能为空！");
				} else {
					com.setRam(ram);
				}
				String content = request.getParameter("content");
				if (content == null || content.equals("")) {
					sbf.append("评价不能为空！");
				} else {
					com.setContent(content);
				}
				String pixels = request.getParameter("pixels");
				if (pixels == null || pixels.equals("")) {
					sbf.append("像素不能为空！");
				} else {
					com.setPixels(pixels);

				}
				String imgPath = request.getParameter("imgPath");
				if (imgPath != null && !imgPath.equals(""))
					com.setImgPath(imgPath);

				int num = Integer.parseInt(request.getParameter("num"));
				com.setNum(num);
				if (sbf.length() != 0) {
					request.setAttribute("msg", sbf.toString());

					edit(request, response);

				} else {
					if (coms.update(com)) {
						request.setAttribute("msg", "修改成功！");
						list(request, response);

					} else {

						request.setAttribute("msg", "修改失败！！");
						edit(request, response);

					}

				}
			} else {
				request.setAttribute("msg", "修改失败！！");
				list(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void changeState(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Computer com = null;
		ComputerService coms = null;
		int id = Integer.parseInt(request.getParameter("id"));
		String status = request.getParameter("status");
		try {
			coms = new ComputerService();
			com = coms.get(id);
			if (com != null) {
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String strDate = dateformat.format(date);
				Date regDate = java.sql.Date.valueOf(strDate);
				com.setReg_date(regDate);
				int state = Integer.parseInt(status);
				if (state == 0) {
					com.setStatus(1);
				} else {
					com.setStatus(0);
				}
				if (coms.updateStatus(com)) {
					list(request, response);
				} else {
					list(request, response);
				}
			} else {
				request.setAttribute("msg", "find not id");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	/*
	 * 后台电脑显示
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageNo = 1;
		int pageSize = 8;
		int count = 0;
		List<Computer> comList = null;
		String keyWord;
		List<Brand> bList = null;
		List<Screen> sList = null;
		List<Cpu> cList = null;
		StringBuffer sbf = null;
		ScreenService ss = null;
		CpuService cs = null;
		BrandService bs = null;
		ComputerService coms = null;
		boolean flag = false;
		String pn = request.getParameter("pageNo");
		String status = request.getParameter("status");
		String brandName = request.getParameter("brand");
		String colorName = request.getParameter("color");
		String moderName = request.getParameter("model");
		String start = request.getParameter("start");
		String over = request.getParameter("over");
		try {

			sbf = new StringBuffer();
			coms = new ComputerService();
			if (pn != null && !pn.equals(""))
				pageNo = Integer.parseInt(pn);

			if (pageNo < 1)
				pageNo = 1;

			if (status != null && !status.equals("")) {
				sbf.append(" status ='");
				sbf.append(status);
				sbf.append("'");
				flag = true;
			}
			if (brandName != null && !brandName.equals("")) {
				if (flag)
					sbf.append(" and ");
				sbf.append(" brand_name like '%");
				sbf.append(brandName);
				sbf.append("%' ");
				flag = true;
			}
			if (colorName != null && !colorName.equals("")) {
				if (flag)
					sbf.append(" and ");
				sbf.append(" color like '%");
				sbf.append(colorName);
				sbf.append("%' ");
				flag = true;
			}
			if (moderName != null && !moderName.equals("")) {
				if (flag)
					sbf.append(" and ");
				sbf.append(" model like '%");
				sbf.append(moderName);
				sbf.append("%' ");
				flag = true;
			}
			if (start != null && !start.equals("") || over != null && !over.equals("")) {
				if (flag)
					sbf.append(" and ");
				sbf.append(" reg_date BETWEEN ' ");
				sbf.append(start);
				sbf.append(" ' and '");
				sbf.append(over);
				sbf.append(" '");
				flag = true;
			}
			if (flag) {
				keyWord = "where";
			} else {
				keyWord = "";
			}
			if (sbf.toString().equals("")) {
				comList = coms.list(pageSize, pageNo, keyWord);
				count = coms.getCount(keyWord);
			} else {
				comList = coms.list(pageSize, pageNo, keyWord + sbf.toString());
				count = coms.getCount(keyWord + sbf.toString());
			}
			int totalPage = (int) Math.ceil((double) count / pageSize);
			if (pageNo >= totalPage)
				pageNo = totalPage;
			request.setAttribute("comList", comList);
			cs = new CpuService();
			cList = cs.listCpu();
			request.setAttribute("cList", cList);

			bs = new BrandService();
			bList = bs.listBrand();
			request.setAttribute("bList", bList);

			ss = new ScreenService();
			sList = ss.listScreen();

			request.setAttribute("sList", sList);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("count", count);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalPage", totalPage);
			request.getRequestDispatcher("computer/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StringBuffer sbf = null;
		Cpu cpu = null;
		Screen screen = null;
		Brand brand = null;
		Computer com = null;
		ComputerService coms = null;
		ScreenService ss = null;
		BrandService bs = null;
		CpuService cs = null;
		try {
			com = new Computer();
			cs = new CpuService();
			bs = new BrandService();
			ss = new ScreenService();
			coms = new ComputerService();
			sbf = new StringBuffer();
			String brandId = request.getParameter("brandName");
			if (brandId == null || brandId.equals("")) {
				sbf.append("品牌名称不能为空！");

			} else {
				int bi = Integer.parseInt(brandId);
				brand = bs.getBrand(bi);
				com.setBrand(brand);
			}

			String model = request.getParameter("model");
			if (model.equals("") || model == null) {
				sbf.append("电脑型号不能为空！");
			} else {

				com.setModer(model);
			}
			String cpuId = request.getParameter("cpuName");

			if (cpuId == null || cpuId.equals("")) {
				sbf.append("处理器不能为空！");
			} else {
				int ci = Integer.parseInt(cpuId);
				cpu = cs.getCpu(ci);
				com.setCpu(cpu);
			}
			String screenId = request.getParameter("screenName");
			if (screenId == null || screenId.equals("")) {
				sbf.append("屏幕尺寸不能为空！");
			} else {
				int si = Integer.parseInt(screenId);
				screen = ss.getScreen(si);
				com.setScreen(screen);
			}
			String color = request.getParameter("color");
			if (color == null || color.equals("")) {
				sbf.append("颜色不能为空！");
			} else {
				com.setColor(color);
			}
			String price = request.getParameter("price");
			if (price == null || price.equals("")) {
				sbf.append("价格不能为空！");
			} else {
				double p = Double.parseDouble(price);
				com.setPrice(p);
			}
			String realPrice = request.getParameter("relPrice");
			if (realPrice == null || realPrice.equals("")) {
				sbf.append("售价不能为空！");
			} else {
				double p = Double.parseDouble(realPrice);
				com.setRel_price(p);
			}
			String ram = request.getParameter("ram");
			if (ram == null || ram.equals("")) {
				sbf.append("内存不能为空！");
			} else {
				com.setRam(ram + "G");
			}
			String pixels = request.getParameter("pixels");
			if (pixels == null || pixels.equals("")) {
				sbf.append("像素不能为空！");
			} else {
				com.setPixels(pixels);

			}
			String num = request.getParameter("num");
			if (num == null || num.equals("")) {
				sbf.append("库存不能为空！");
			} else {
				int n = Integer.parseInt(num);
				com.setNum(n);
			}
			String content = request.getParameter("content");
			if (content == null || content.equals("")) {
				sbf.append("评价不能为空！");
			} else {
				com.setContent(content);
			}

			String imgPath = request.getParameter("imgPath");
			com.setImgPath(imgPath);
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String strDate = dateformat.format(date);
			Date regDate = java.sql.Date.valueOf(strDate);
			com.setReg_date(regDate);
			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				list(request, response);
			} else {

				if (coms.save(com)) {
					request.setAttribute("msg", "添加成功！");
					list(request, response);

				} else {
					System.out.println(com);
					request.setAttribute("msg", "添加失败！！");
					create(request, response);

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
