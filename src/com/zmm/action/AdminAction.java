package com.zmm.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zmm.dto.Admin;
import com.zmm.dto.User;
import com.zmm.service.AdminService;
import com.zmm.service.UserService;

/**
 * Servlet implementation class AdminAction
 */
@WebServlet("/AdminAction")
public class AdminAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAction() {
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
			if (operate.equals("quit")) {
				quit(request, response);
			} else if (operate.equals("login")) {
				login(request, response);
			} else if (operate.equals("update")) {
				update(request, response);
			} else if (operate.equals("modify")) {
				modify(request, response);
			} else if (operate.equals("delete")) {
				delete(request, response);
			} else if (operate.equals("add")) {
				add(request, response);
			} else if (operate.equals("list")) {
				list(request, response);
			} else if (operate.equals("create")) {
				create(request, response);
			} else if (operate.equals("recycleBin")) {
				recycleBin(request, response);
			} else if (operate.equals("recycleBinList")) {
				recycleBinList(request, response);
			} else if (operate.equals("recover")) {
				recover(request, response);
			} else if (operate.equals("clearRecycleBin")) {
				clearRecycleBin(request, response);
			} else if (operate.equals("modifyPsw")) {
				modifyPsw(request, response);
			}

		}

	}

	private void modifyPsw(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		StringBuffer sbf = new StringBuffer();
		String oldPsw = request.getParameter("oldPsw");
		AdminService as = null;
		Admin ad = null;
		HttpSession session = request.getSession();
		session.getAttribute("adminName");
		try {
			as = new AdminService();
			String adminName = request.getParameter("adminName");
			if (oldPsw == null || oldPsw.equals("")) {
				sbf.append("ԭ���벻��Ϊ��!<br/>");
			}
			ad = as.getPassword(oldPsw, adminName);
			if (ad != null) {
				String newPsw = request.getParameter("newPsw");
				String againPsw = request.getParameter("againPsw");
				if (newPsw == null || newPsw.equals("")) {
					sbf.append("�����벻��Ϊ�գ�<br/>");
				}
				if (againPsw == null || againPsw.equals("")) {
					sbf.append("�ٴ����������벻��Ϊ�գ�<br/>");
				}
				if (oldPsw.equals(newPsw)) {
					sbf.append("�����벻��ԭ��������ͬ��<br/>");
				}
				if (newPsw.equals(againPsw)) {
					ad.setAdmin_password(newPsw);
				} else {
					sbf.append("�����������벻ͬ��<br/>");
				}
			} else {
				sbf.append("ԭ�����������<br/>");
			}

			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("admin/reset-password.jsp").forward(request, response);
			} else {
				if (as.update(ad)) {
					request.setAttribute("msg", "�޸�����ɹ���");
					request.getRequestDispatcher("sign-in.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "�޸�����ʧ��");
					request.getRequestDispatcher("admin/reset-password.jsp").forward(request, response);

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void clearRecycleBin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		AdminService as = null;
		try {
			as = new AdminService();

			if (as.deleteStatus()) {
				request.setAttribute("msg", "��ճɹ���");
				recycleBinList(request, response);
			} else {
				request.setAttribute("msg", "���ʧ�ܣ���");
				recycleBinList(request, response);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void recover(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		AdminService as = null;
		Admin ad = null;
		try {
			as = new AdminService();
			ad = as.getById(id);
			ad.setStatus(1);
			if (ad != null) {
				if (as.updateStatus(ad)) {
					request.setAttribute("msg", "��ԭ�ɹ���");
					list(request, response);
				} else {
					request.setAttribute("msg", "��ԭʧ�ܣ���");
					recycleBinList(request, response);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void recycleBinList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<Admin> aList = null;
		AdminService as = null;
		int pageNo = 1, pageSize = 6, count = 0;
		String pn=request.getParameter("pageNo");
		Admin ad = new Admin();
		try {
			as = new AdminService();
			ad.setStatus(0);
			count = as.getCount(ad);
			aList = new ArrayList<Admin>();
			aList = as.recycleBinList(pageNo, pageSize);
			int totalPage=(int) Math.ceil((double) count/pageSize);
			if (pn != null && !pn.equals("")) {
				if(Integer.parseInt(pn)<=0){
					pageNo=1;
				}else if(Integer.parseInt(pn)>totalPage){
					pageNo=totalPage;
				}else{
					pageNo = Integer.parseInt(pn);

				}
			}
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("aList", aList);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("count", count);
			request.getRequestDispatcher("admin/recycleList.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void recycleBin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		AdminService as = null;
		Admin ad = null;
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			as = new AdminService();
			ad = as.getById(id);
			ad.setStatus(0);
			if (ad != null) {
				if (as.updateStatus(ad)) {
					request.setAttribute("msg", "ɾ���ɹ���");
					list(request, response);
				} else {
					request.setAttribute("msg", "ɾ��ʧ�ܣ�");
					list(request, response);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void create(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.getRequestDispatcher("admin/add.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<Admin> aList = null;
		AdminService as = null;
		int pageNo = 1, pageSize = 6, count = 0;
		String pn = request.getParameter("pageNo");
		Admin ad = new Admin();
		ad.setStatus(1);
		try {
			as = new AdminService();
			count = as.getCount(ad);
			int totalPage = (int) Math.ceil((double) count / pageSize);
			if (pn != null && !pn.equals("")) {
				if(Integer.parseInt(pn)<=0){
					pageNo=1;
				}else if(Integer.parseInt(pn)>totalPage){
					pageNo=totalPage;
				}else{
					pageNo = Integer.parseInt(pn);

				}
			}
			aList = as.listAdmin(pageNo, pageSize);
			request.setAttribute("pageSize",pageSize);
			request.setAttribute("aList", aList);
			request.setAttribute("count", count);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalPage", totalPage);
			request.getRequestDispatcher("admin/list.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		StringBuffer sbf = null;
		String adminName = request.getParameter("adminName");
		String adminPassword = request.getParameter("adminPassword");
		Admin ad = null;
		AdminService as = null;
		try {
			ad = new Admin();
			sbf = new StringBuffer();
			as = new AdminService();
			if (adminName == null || adminName.equals("")) {
				sbf.append("����Ա�û�������Ϊ�գ�");
			} else {
				ad.setAdmin_name(adminName);
			}
			if (adminPassword == null || adminPassword.equals("")) {
				sbf.append("����Ա���벻��Ϊ�գ�");
			} else {
				ad.setAdmin_password(adminPassword);

			}
			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("admin/add.jsp").forward(request, response);
			} else {
				if (as.add(ad)) {
					request.setAttribute("msg", "��ӳɹ�");
					list(request, response);
				} else {
					request.setAttribute("msg", "���ʧ��");
					request.getRequestDispatcher("admin/add.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		AdminService as = null;
		Admin ad = null;
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			as = new AdminService();
			ad = as.getById(id);
			if (ad != null) {
				if (as.delete(id)) {
					request.setAttribute("msg", "����ɾ��");
					recycleBinList(request, response);
				} else {
					request.setAttribute("msg", "ʧ��");
					recycleBinList(request, response);
				}
			} else {
				request.setAttribute("msg", "�Ҳ���");
				recycleBinList(request, response);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void modify(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		Admin ad = null;
		AdminService as = null;
		try {
			as = new AdminService();
			ad = as.getById(id);
			request.setAttribute("admin", ad);
			request.getRequestDispatcher("admin/update.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		AdminService as = null;
		Admin ad = null;
		int id = Integer.parseInt(request.getParameter("id"));
		String adminName = request.getParameter("adminName");
		StringBuffer sbf = null;
		try {
			sbf = new StringBuffer();
			as = new AdminService();
			ad = as.getById(id);
			if (adminName == null || adminName.equals("")) {
				sbf.append("����Ա�û�����Ϊ��");
			} else {
				ad.setAdmin_name(adminName);
			}	
			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("admin/update.jsp").forward(request, response);
			} else {
				if (ad != null) {
					if (as.update(ad)) {
						request.setAttribute("msg", "�޸ĳɹ�");
						list(request, response);
					} else {
						request.setAttribute("msg", "�޸�ʧ�ܣ�");
						list(request, response);
					}
				} else {
					request.setAttribute("msg", "�Ҳ�����");
					request.getRequestDispatcher("admin/update.jsp").forward(request, response);

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void quit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		try {
			if (session != null) {
				session.invalidate();//�������
				request.getRequestDispatcher("sign-in.jsp").forward(request, response);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String adminName = request.getParameter("adminName");
		String adminPassword = request.getParameter("adminPassword");
		StringBuffer sbf = null;
		AdminService as = null;
		List<Admin> aList = null;
		List<User> uList = null;
		UserService us = new UserService();
		int pageNo = 1, pageSize = 3;
		try {
			as = new AdminService();
			sbf = new StringBuffer();
			if (adminName == null || adminName.equals("")) {
				sbf.append("�û�������Ϊ�գ�");
			}
			if (adminPassword == null || adminPassword.equals("")) {
				sbf.append("���벻��Ϊ�գ�");
			}
			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("sign-in.jsp").forward(request, response);
			} else {
				Admin ad = as.login(adminName, adminPassword);
				HttpSession session = request.getSession();
				session.setAttribute("adminName", adminName);
				session.setAttribute("admin", ad);
				if (ad != null) {
					aList = as.listAdmin(pageNo, pageSize);
					request.setAttribute("aList", aList);
					uList = us.listUser(pageNo, pageSize);
					request.setAttribute("uList", uList);
					request.getRequestDispatcher("admin/index.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "�û������������ ");
					request.getRequestDispatcher("sign-in.jsp").forward(request, response);

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
