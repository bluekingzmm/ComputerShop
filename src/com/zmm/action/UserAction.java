package com.zmm.action;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zmm.dto.User;
import com.zmm.service.OrderService;
import com.zmm.service.UserService;
import com.zmm.util.Mail;
import com.zmm.util.MailUtils;
import com.zmm.util.Uuid;

/**
 * Servlet implementation class UserAction
 */
@WebServlet("/UserAction")
public class UserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAction() {
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
		String operate = request.getParameter("operate");
		if (operate == null || operate.equals("")) {
			return;
		} else {
			if (operate.equals("login")) {
				login(request, response);
			} else if (operate.equals("add")) {
				add(request, response);
			} else if (operate.equals("delete")) {
				delete(request, response);
			} else if (operate.equals("update")) {
				update(request, response);
			} else if (operate.equals("modify")) {
				modify(request, response);
			} else if (operate.equals("search")) {
				search(request, response);
			} else if (operate.equals("list")) {
				list(request, response);
			} else if (operate.equals("recycleBinList")) {
				recycleBinList(request, response);
			} else if (operate.equals("recycleBin")) {
				recycleBin(request, response);
			} else if (operate.equals("recover")) {
				recover(request, response);
			} else if (operate.equals("quit")) {
				quit(request, response);
			} else if (operate.equals("activate")) {
				activate(request, response);
			} else if (operate.equals("modifyPsw")) {
				modifyPsw(request, response);
			} else if (operate.equals("yzm")) {
				yzm(request, response);
			} else if (operate.equals("modifyPsw1")) {
				modifyPsw1(request, response);
			} else if (operate.equals("modifyPsw2")) {
				modifyPsw2(request, response);
			} else if (operate.equals("modifyPsw3")) {
				modifyPsw3(request, response);
			} else if (operate.equals("show")) {
				show(request, response);
			} else if (operate.equals("mail")) {
				mail(request, response);
			} else if (operate.equals("searchUserName")) {
				searchUserName(request, response);
			} else if (operate.equals("noActive")) {
				noActive(request, response);
			} else if (operate.equals("deleteOrder")) {
				deleteOrder(request, response);
			} else if (operate.equals("SendMail")) {
				SendMail(request, response);
			}else if(operate.equals("pMail")){
				pMail(request,response);
			}
		}

	}

	private void pMail(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String u = request.getParameter("userName");// ��ȡ�û���
		try {
			session.setAttribute("UserName", u);
		
			request.getRequestDispatcher("user/modifyPsw/forgetPwd5.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * �޸�����ʱ��������
	 */
	private void SendMail(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession s = request.getSession();
		String mail = request.getParameter("mail");
		String ue = request.getParameter("userName");
		UserService us = null;
		User user = null;
		StringBuffer sbf = null;
		try {
			sbf = new StringBuffer();
			us = new UserService();
			if (mail == null || mail.equals("")) {
				sbf.append("���䲻��Ϊ�գ�");
			}
			if (ue == null || ue.equals("")) {
				sbf.append("�û�������Ϊ�գ�");
			}

			user = us.searchMail(mail, ue);// ���������ҵ��û�

			if (user != null) {
				s.setAttribute("userName", ue);

				if (sbf.length() != 0) {
					request.setAttribute("msg", sbf.toString());
					request.getRequestDispatcher("user/login.jsp").forward(request, response);
				} else {
					
						Properties props = new Properties();
						// ��������е���������Դ���ļ�����this.getClass().getClassLoader().getResourceAsStream
						props.load(this.getClass().getClassLoader().getResourceAsStream("send.properties"));
						String host = props.getProperty("host");// ��ȡ����������
						String userName = props.getProperty("userName");//
						// ��ȡ�û���
						String userPassword = props.getProperty("userPassword");// ��ȡ����
						
						String from = props.getProperty("from");// ��ȡ������
						String to = user.getMail();// ��ȡ�ռ���
						String subject = props.getProperty("subject");// ��ȡ����
//						s.setAttribute("userName", ue);
//						String content = "<a href='http://10.101.32.192:8080/ComputerShop/UserAction?operate=pMail&userName={0}'>��������޸�ҳ��</a>";
						String content=props.getProperty("content");
						content = MessageFormat.format(content, ue);
						Session session = MailUtils.createSession(host, userName, userPassword);// �õ�session


						Mail email = new Mail(from, to, subject, content);
						// �����ʼ�����

						MailUtils.send(session, email);// ���ʼ���

						request.setAttribute("msg", "�����ϵ�����鿴��Ϣ");
						request.getRequestDispatcher("user/login.jsp").forward(request, response);
					}

				}
//			} else {
//				request.setAttribute("msg", "���䲻�ԣ������ԣ�");
//				request.getRequestDispatcher("user/login.jsp").forward(request, response);
//
//			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	// ǰ̨�û�ȡ������
	private void deleteOrder(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id"));
		OrderService os = null;
		try {
			os = new OrderService();

			if (os.deleteOrder(id)) {

				request.getRequestDispatcher("OrderAction?operate=listOrder").forward(request, response);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}

	// ��̨��ʾδ������û�
	private void noActive(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<User> uList = null;
		UserService us = null;
		int pageNo = 1, pageSize = 6, count = 0;
		User user = new User();
		String pn = request.getParameter("pageNo");

		try {
			us = new UserService();
			user.setStatus(0);
			count = us.getUserCount(user);
			uList = us.recycleBinList(pageNo, pageSize, user);
			int totalPage = (int) Math.ceil((double) count / pageSize);
			if (pn != null && !pn.equals("")) {
				if (Integer.parseInt(pn) <= 0) {
					pageNo = 1;
				} else if (Integer.parseInt(pn) > totalPage) {
					pageNo = totalPage;
				} else {
					pageNo = Integer.parseInt(pn);

				}
			}
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("uList", uList);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("count", count);
			request.getRequestDispatcher("user/recycleList.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * ��̨�����û������в�ѯ
	 */
	private void searchUserName(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<User> uList = null;
		UserService us = null;
		String userName = request.getParameter("userName");
		try {

			us = new UserService();
			uList = us.search(userName);
			request.setAttribute("uList", uList);
			request.getRequestDispatcher("user/search.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * ǰ̨�û�δ����ʱ���ͼ�����
	 */
	private void mail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String mail = request.getParameter("mail");
		String ue = request.getParameter("userName");
		UserService us = null;
		User user = null;
		StringBuffer sbf = null;
		try {
			sbf = new StringBuffer();
			us = new UserService();
			if (mail == null || mail.equals("")) {
				sbf.append("���䲻��Ϊ�գ�");
			}
			if (ue == null || ue.equals("")) {
				sbf.append("�û�������Ϊ�գ�");
			}

			user = us.searchMail(mail, ue);// ���������ҵ��û�

			if (user != null) {
				if (sbf.length() != 0) {
					request.setAttribute("msg", sbf.toString());
					request.getRequestDispatcher("user/mail.jsp").forward(request, response);
				} else {
					user.setCode(Uuid.uuid());// ������
					user.setStatus(1);// ���ü���û�����״̬����Ϊ1
					if (us.modifyOwn(user)) {
						Properties props = new Properties();
						// ��������е���������Դ���ļ�����this.getClass().getClassLoader().getResourceAsStream
						props.load(this.getClass().getClassLoader().getResourceAsStream("email.properties"));
						String host = props.getProperty("host");// ��ȡ����������
						String userName = props.getProperty("userName");//
						// ��ȡ�û���
						String userPassword = props.getProperty("userPassword");// ��ȡ����

						String from = props.getProperty("from");// ��ȡ������
						String to = user.getMail();// ��ȡ�ռ���
						String subject = props.getProperty("subject");// ��ȡ����
						String content = props.getProperty("content"); // ��ȡ�ʼ�����

						// �ַ���ģ������滻
						content = MessageFormat.format(content, user.getCode());// �滻�����ļ��е�{0}
																				// content
																				// ����Ҫ���滻�ľ���{}�еĲ���

						Session session = MailUtils.createSession(host, userName, userPassword);// �õ�session

						Mail email = new Mail(from, to, subject, content);
						// �����ʼ�����

						MailUtils.send(session, email);// ���ʼ���

						request.setAttribute("msg", "�����ϵ����伤��");
						request.getRequestDispatcher("user/login.jsp").forward(request, response);
					}

				}
			} else {
				request.setAttribute("msg", "���䲻�ԣ������ԣ�");
				request.getRequestDispatcher("user/login.jsp").forward(request, response);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// ǰ̨��ʾ�Լ�����Ϣ
	private void show(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		User user = null;
		UserService us = null;
		try {
			us = new UserService();

			user = us.getById(id);
			request.setAttribute("user", user);
			request.getRequestDispatcher("user/user.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * ǰ̨�޸��û����루��ȫ�������룩���������裨�����µ����룩
	 */
	private void modifyPsw3(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String newPsw = request.getParameter("newPsw");
		String againPsw = request.getParameter("againPsw");
		StringBuffer sbf = null;
		UserService us = null;
		try {
			us = new UserService();
			sbf = new StringBuffer();
			if (newPsw == null || newPsw.equals("")) {
				sbf.append("�����벻��Ϊ�գ�");
			}
			if (againPsw == null || againPsw.equals("")) {
				sbf.append("���ٴ��������룡");
			}
			if (!newPsw.equals(againPsw)) {
				sbf.append("�����������벻ͬ��");
			}
			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("user/modifyPsw/forgetPwd3.jsp").forward(request, response);
			} else {
				if (us.updatePsw(newPsw, userName)) {
					request.setAttribute("msg", "�޸�����ɹ���");
					request.getRequestDispatcher("user/modifyPsw/forgetPwd4.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "�޸�����ʧ��");
					request.getRequestDispatcher("user/modifyPsw/forgetPwd3.jsp").forward(request, response);

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/*
	 * ǰ̨�޸��û����루��ȫ�������룩�ڶ������裨�����û����������ַ��ѯ��ƥ�����ݿ��û���ǰע�������Ƿ���ȷ��
	 */
	private void modifyPsw2(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String mail = request.getParameter("mail");
		UserService us = null;
		StringBuffer sbf = null;
		try {
			us = new UserService();
			sbf = new StringBuffer();
			if (mail == null || mail.equals("")) {
				sbf.append("���䲻��Ϊ�գ�");
			}
			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("user/modifyPsw/forgetPwd2.jsp").forward(request, response);
			} else {
				if (us.searchMailName(mail)) {
					request.getRequestDispatcher("user/modifyPsw/forgetPwd3.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "���䲻��ȷ��");
					request.getRequestDispatcher("user/modifyPsw/forgetPwd2.jsp").forward(request, response);

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/*
	 * ǰ̨�޸��û����루��ȫ�������룩��һ�����裨�����û������û�������ʵ�û��Ƿ�ע�����
	 */
	private void modifyPsw1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String checkCode = request.getParameter("checkCode");
		StringBuffer sbf = new StringBuffer();
		HttpSession session = request.getSession();
		UserService us = null;
		try {
			if (userName == null || userName.equals("")) {
				sbf.append("�û�������Ϊ�գ�");
			}
			if (checkCode == null || checkCode.equals("")) {
				sbf.append("��֤�벻�ܿգ�");
			}
			us = new UserService();
			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("user/modifyPsw/index.jsp").forward(request, response);
			} else {
				if (us.searchUsername(userName)) {
					if (!checkCode.equalsIgnoreCase((String) session.getAttribute("randCheckCode"))) {
						request.setAttribute("msg", "��֤�����");
						request.getRequestDispatcher("user/modifyPsw/index.jsp").forward(request, response);
					} else {
						session.setAttribute("userName", userName);
						request.getRequestDispatcher("user/modifyPsw/forgetPwd2.jsp").forward(request, response);

					}

				} else {
					request.setAttribute("msg", "�û��������ڣ�");
					request.getRequestDispatcher("user/modifyPsw/index.jsp").forward(request, response);

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public Color getRandColor(int s, int e) {
		Random random = new Random();
		if (s > 255)
			s = 255;
		if (e > 255)
			e = 255;
		int r, g, b;
		r = s + random.nextInt(e - s); // �������RGB��ɫ�е�rֵ
		g = s + random.nextInt(e - s); // �������RGB��ɫ�е�gֵ
		b = s + random.nextInt(e - s); // �������RGB��ɫ�е�bֵ
		return new Color(r, g, b);
	}

	public void yzm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ���ò�����ͼƬ
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// ָ�����ɵ���ӦͼƬ,һ������ȱ����仰,�������.
		response.setContentType("image/jpeg");
		int width = 112, height = 37; // ָ��������֤��Ŀ�Ⱥ͸߶�
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // ����BufferedImage����,�������൱��һͼƬ
		Graphics g = image.getGraphics(); // ����Graphics����,�������൱�ڻ���
		Graphics2D g2d = (Graphics2D) g; // ����Grapchics2D����
		Random random = new Random();
		Font mfont = new Font("����", Font.BOLD, 24); // ����������ʽ
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height); // ���Ʊ���
		g.setFont(mfont); // ��������
		g.setColor(getRandColor(180, 200));

		// ����100����ɫ��λ��ȫ��Ϊ�������������,������Ϊ2f
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int x1 = random.nextInt(6) + 1;
			int y1 = random.nextInt(12) + 1;
			BasicStroke bs = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL); // ����������ʽ
			Line2D line = new Line2D.Double(x, y, x + x1, y + y1);
			g2d.setStroke(bs);
			g2d.draw(line); // ����ֱ��
		}
		// �����Ӣ�ģ����֣������������ɵ���֤���֣��������Ϸ�ʽ�������������ȷ����
		String sRand = "";
		String ctmp = "";
		int itmp = 0;
		// �ƶ��������֤��Ϊ��λ
		for (int i = 0; i < 4; i++) {
			switch (random.nextInt(3)) {
			case 1: // ����A-Z����ĸ
				itmp = random.nextInt(26) + 65;
				ctmp = String.valueOf((char) itmp);
				break;
			case 2: // ���ɺ���
				String[] rBase = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
				// ���ɵ�һλ����
				int r1 = random.nextInt(3) + 11;
				String str_r1 = rBase[r1];
				// ���ɵڶ�λ����
				int r2;
				if (r1 == 13) {
					r2 = random.nextInt(7);
				} else {
					r2 = random.nextInt(16);
				}
				String str_r2 = rBase[r2];
				// ���ɵ�һλλ��
				int r3 = random.nextInt(6) + 10;
				String str_r3 = rBase[r3];
				// ���ɵڶ�λλ��
				int r4;
				if (r3 == 10) {
					r4 = random.nextInt(15) + 1;
				} else if (r3 == 15) {
					r4 = random.nextInt(15);
				} else {
					r4 = random.nextInt(16);
				}
				String str_r4 = rBase[r4];
				// �����ɵĻ�����ת��Ϊ����
				byte[] bytes = new byte[2];
				// �����ɵ����뱣�浽�ֽ�����ĵ�һ��Ԫ����
				String str_12 = str_r1 + str_r2;
				int tempLow = Integer.parseInt(str_12, 16);
				bytes[0] = (byte) tempLow;
				// �����ɵ�λ�뱣�浽�ֽ�����ĵڶ���Ԫ����
				String str_34 = str_r3 + str_r4;
				int tempHigh = Integer.parseInt(str_34, 16);
				bytes[1] = (byte) tempHigh;
				ctmp = new String(bytes);
				break;
			default:
				itmp = random.nextInt(10) + 48;
				ctmp = String.valueOf((char) itmp);
				break;
			}
			sRand += ctmp;
			Color color = new Color(20 + random.nextInt(110), 20 + random.nextInt(110), random.nextInt(110));
			g.setColor(color);
			// �����ɵ����������������Ų���ת�ƶ��Ƕ� PS.���鲻Ҫ�����ֽ�����������ת,��Ϊ����ͼƬ���ܲ�������ʾ
			/* ��������ת�ƶ��Ƕ� */
			Graphics2D g2d_word = (Graphics2D) g;
			AffineTransform trans = new AffineTransform();
			trans.rotate((45) * 3.14 / 180, 15 * i + 8, 7);
			/* �������� */
			float scaleSize = random.nextFloat() + 0.8f;
			if (scaleSize > 1f)
				scaleSize = 1f;
			trans.scale(scaleSize, scaleSize);
			g2d_word.setTransform(trans);
			g.drawString(ctmp, 15 * i + 18, 14);
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("randCheckCode", sRand);
		g.dispose(); // �ͷ�g��ռ�õ�ϵͳ��Դ
		ImageIO.write(image, "JPEG", response.getOutputStream()); // ���ͼƬ
	}

	/*
	 * ǰ̨�޸��û����루��¼�������룩�Զ���ȡԭ���룩
	 */
	private void modifyPsw(HttpServletRequest request, HttpServletResponse response) {

		// TODO Auto-generated method stub
		
		StringBuffer sbf = new StringBuffer();
		String oldPsw = request.getParameter("oldPsw");
		String newPsw = request.getParameter("newPsw");
		String againPsw = request.getParameter("againPsw");
		UserService us = null;
		User user = null;
		try {
			us = new UserService();
			String userName = request.getParameter("userName");
			if (oldPsw == null || oldPsw.equals("")) {
				sbf.append("ԭ���벻��Ϊ��!<br/>");
			}
			user = us.getPassword(oldPsw, userName);

			if (user != null) {

				if (newPsw == null || newPsw.equals("")) {
					sbf.append("�����벻��Ϊ�գ�<br/>");
				}
				if (againPsw == null || againPsw.equals("")) {
					sbf.append("�ٴ����������벻��Ϊ�գ�<br/>");
				}
				if (oldPsw.equals(newPsw)) {
					sbf.append("�����벻��ԭ��������ͬ��<br/>");
				}
				if (!newPsw.equals(againPsw)) {
					sbf.append("�����������벻ͬ��<br/>");
				}
			} else {
				sbf.append("ԭ�����������<br/>");
			}

			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("user/modifyPsw.jsp").forward(request, response);
			} else {
				if (us.updatePsw(newPsw, userName)) {
					request.setAttribute("msg", "�޸�����ɹ���");
					request.getRequestDispatcher("user/login.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "�޸�����ʧ��");
					request.getRequestDispatcher("user/modifyPsw.jsp").forward(request, response);

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// �����û�
	private void activate(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		User user = null;
		UserService us = null;
		String code = request.getParameter("code");// ��ȡ������
		try {
			us = new UserService();
			user = us.active(code);// ��ѯ�������Ƿ����
			if (user == null) {
				request.setAttribute("msg", "��������Ч");
			} else if (user.getStatus() == 1) {
				request.setAttribute("msg", "���Ѿ�������ˣ�");
			} else {
				user.setStatus(1);
				us.updateStatus(user);
				request.setAttribute("msg", "����ɹ������¼");

			}
			request.getRequestDispatcher("user/register.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// �û��˳�
	private void quit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		try {
			if (session != null) {
				session.invalidate();// ɾ��session���ݣ�sessionʧЧ��
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// ��̨��ԭ�û����ӻ���վ�л�ԭ�û���
	private void recover(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		UserService us = null;
		User user = null;
		try {
			us = new UserService();
			user = us.getById(id);
			if (user != null) {
				user.setStatus(1);// ���û�״̬�ı䣨����Ϊ1��
				if (us.updateStatus(user)) {
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

	// ���û�ɾ��������վ
	private void recycleBin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		UserService us = null;
		User user = null;
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			us = new UserService();
			user = us.getById(id);
			user.setStatus(-1);// �����û�״̬Ϊ-1
			if (user != null) {
				if (us.updateStatus(user)) {
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

	// ��ʾ����վ���б�
	private void recycleBinList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<User> uList = null;
		UserService us = null;
		int pageNo = 1, pageSize = 6, count = 0;
		User user = new User();
		String pn = request.getParameter("pageNo");
		try {
			us = new UserService();
			user.setStatus(-1);
			count = us.getUserCount(user);
			uList = us.recycleBinList(pageNo, pageSize, user);
			int totalPage = (int) Math.ceil((double) count / pageSize);// �õ���ҳ��
			if (pn != null && !pn.equals("")) {
				if (Integer.parseInt(pn) <= 0) {
					pageNo = 1;
				} else if (Integer.parseInt(pn) > totalPage) {
					pageNo = totalPage;
				} else {
					pageNo = Integer.parseInt(pn);

				}
			}
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("uList", uList);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("count", count);
			request.getRequestDispatcher("user/recycleList.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// ��ʾ�û��б�
	private void list(HttpServletRequest request, HttpServletResponse response) {

		List<User> uList = null;
		UserService us = null;
		int pageNo = 1, pageSize = 6, count = 0;
		User user = new User();
		user.setStatus(1);
		String pn = request.getParameter("pageNo");
		try {
			us = new UserService();
			count = us.getUserCount(user);
			int totalPage = (int) Math.ceil((double) count / pageSize);
			if (pn != null && !pn.equals("")) {
				if (Integer.parseInt(pn) <= 0) {
					pageNo = 1;
				} else if (Integer.parseInt(pn) > totalPage) {
					pageNo = totalPage;
				} else {
					pageNo = Integer.parseInt(pn);

				}
			}
			uList = us.listUser(pageNo, pageSize);
			request.setAttribute("uList", uList);
			request.setAttribute("count", count);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("pageSize", pageSize);
			request.getRequestDispatcher("user/list.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// �鿴�Լ���Ϣ
	private void search(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int user_id = Integer.parseInt(request.getParameter("id"));
		UserService us = new UserService();
		User user = new User();
		try {
			user = us.getById(user_id);
			if (user != null) {
				request.setAttribute("user", user);
				request.getRequestDispatcher("user/show.jsp").forward(request, response);
			} else {
				list(request, response);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void modify(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		UserService us = new UserService();
		try {
			User user = us.getById(id);
			request.setAttribute("user", user);
			request.getRequestDispatcher("user/OwnInfo.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String mail = request.getParameter("mail");
		String relName = request.getParameter("relName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String Id = request.getParameter("Id");
		int user_id = Integer.parseInt(request.getParameter("id"));
		StringBuffer sbf = null;
		User user = null;
		UserService us = null;
		try {
			us = new UserService();
			user = us.getById(user_id);
			sbf = new StringBuffer();
			if (mail == null || mail.equals("")) {
				sbf.append("���䲻��Ϊ�գ�");
			} else {
				user.setMail(mail);
			}
			if (relName == null || relName.equals("")) {
				sbf.append("��ʵ��������Ϊ�գ�");
			} else {
				user.setRel_name(relName);
			}
			if (Id == null || Id.equals("")) {
				sbf.append("���֤���벻��Ϊ�գ�");
			} else {
				user.setId(Id);
			}

			if (phone == null || phone.equals("")) {
				sbf.append("�ֻ����벻��Ϊ�գ�");
			} else {
				user.setPhone(phone);
			}

			if (address == null || address.equals("")) {
				sbf.append("��ַ����Ϊ�գ�");
			} else {
				user.setAddress(address);
			}
			SimpleDateFormat si = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String d = si.format(date);
			// Id
			int a1 = subStringToId(Id);
			// time
			int a2 = subStringToTime(d);
			int age = a2 - a1;
			user.setAge(age);
			user.setStatus(1);
			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("user/OwnInfo.jsp").forward(request, response);
			} else {
				if (us.modifyOwn(user)) {
					request.setAttribute("msg", "�޸ĳɹ���");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "�޸�ʧ�ܣ���");
					request.getRequestDispatcher("user/OwnInfo.jsp").forward(request, response);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		UserService us = new UserService();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			User user = us.getById(id);
			if (user != null) {
				if (us.delete(id)) {
					request.setAttribute("msg", "ɾ���ɹ���");
					list(request, response);
				} else {
					request.setAttribute("msg", "ɾ��ʧ�ܣ���");
					list(request, response);

				}
			} else {
				response.sendRedirect("errors/404.jsp");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String address = request.getParameter("address");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("phone");
		String Id = request.getParameter("Id");
		String relName = request.getParameter("relName");
		String mail = request.getParameter("mail");
		String agree = request.getParameter("agree");
		StringBuffer sbf = null;
		User user = null;
		UserService us = null;
		try {
			user = new User();
			sbf = new StringBuffer();
			us = new UserService();
			if (userName == null || userName.equals("")) {
				sbf.append("�û�������Ϊ�գ�");
			} else {
				user.setUser_name(userName);
			}
			if (userPassword == null || userPassword.equals("")) {
				sbf.append("���벻��Ϊ�գ�");
			} else {
				user.setUser_password(userPassword);
			}
			if (address == null || address.equals("")) {
				sbf.append("�ջ���ַ����Ϊ�գ�");
			} else {
				user.setAddress(address);

			}
			if (phone == null || phone.equals("")) {
				sbf.append("�ֻ��Ų���Ϊ�գ�");
			} else {
				user.setPhone(phone);
			}
			if (Id == null || Id.equals("")) {
				sbf.append("���֤�Ų���Ϊ�գ�");
			} else {
				user.setId(Id);
			}
			if (relName == null || relName.equals("")) {
				sbf.append("��ʵ��������Ϊ�գ�");
			} else {
				user.setRel_name(relName);
			}
			if (mail == null || mail.equals("")) {
				sbf.append("���䲻��Ϊ�գ�");
			} else {
				user.setMail(mail);
			}
			if (sex.equals("��")) {
				user.setSex(sex);
			} else {
				user.setSex("Ů");
			}
			if (agree.equals("no")) {
				sbf.append("��ͬ�Ȿվ��Լ����ע�ᣡ");
			}
			// ���ڸ�ʽ����
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();// �õ�ϵͳ��ǰʱ��
			String d = dateFormat.format(date);// ��ϵͳ��ǰʱ���ʽ��
			Date reg_time = java.sql.Date.valueOf(d);
			// Id
			int a1 = subStringToId(Id);
			// time
			int a2 = subStringToTime(d);
			int age = a2 - a1;
			user.setAge(age);
			user.setReg_time(reg_time);

			user.setCode(Uuid.uuid());
			System.out.println(user);
			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("user/register.jsp").forward(request, response);
			} else {

				if (us.searchUsername(userName)) {

					request.setAttribute("msg", "�û����Ѿ���ע���ˣ�������ע�ᣡ");
					request.getRequestDispatcher("user/register.jsp").forward(request, response);

				} else {
					if (us.add(user)) {
						/*
						 * ���ʼ� ���������ļ���
						 */
						// ��ȡ�����ļ�����

						Properties props = new Properties();
						props.load(this.getClass().getClassLoader().getResourceAsStream("email.properties"));
						String host = props.getProperty("host");// ��ȡ����������
						String name = props.getProperty("userName");//
						// ��ȡ�û���
						String password = props.getProperty("userPassword");// ��ȡ����

						String from = props.getProperty("from");// ��ȡ������
						String to = user.getMail();// ��ȡ�ռ���
						String subject = props.getProperty("subject");// ��ȡ����
						String content = props.getProperty("content");//
						// ��ȡ�ʼ�����
						content = MessageFormat.format(content, user.getCode());// �滻�����ļ��е�{0}

						Session session = MailUtils.createSession(host, name, password);// �õ�session

						Mail email = new Mail(from, to, subject, content);//
						// �����ʼ�����
						try {
							MailUtils.send(session, email);// ���ʼ���
						} catch (MessagingException e) {
						}
						request.setAttribute("msg", "��ϲ��ע��ɹ��������ϵ����伤��");
						request.getRequestDispatcher("user/register.jsp").forward(request, response);
					} else {
						request.setAttribute("msg", "ע��ʧ�ܣ�");
						request.getRequestDispatcher("user/register.jsp").forward(request, response);
					}
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Id��ȡ
	private int subStringToId(String s) {
		StringBuffer sb = new StringBuffer();
		char ch = s.charAt(6);
		char ch1 = s.charAt(7);
		char ch2 = s.charAt(8);
		char ch3 = s.charAt(9);

		sb.append(ch).append(ch1).append(ch2).append(ch3);
		int Id = Integer.parseInt(sb.toString());
		return Id;
	}

	// ��ǰʱ���ȡ
	private int subStringToTime(String s) {
		StringBuffer sb = new StringBuffer();
		char ch = s.charAt(0);
		char ch1 = s.charAt(1);
		char ch2 = s.charAt(2);
		char ch3 = s.charAt(3);

		sb.append(ch).append(ch1).append(ch2).append(ch3);
		int time = Integer.parseInt(sb.toString());
		return time;
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		UserService us = null;
		StringBuffer sbf = null;
		try {
			us = new UserService();
			sbf = new StringBuffer();
			if (userName == null || userName.equals("")) {
				sbf.append("�û�������Ϊ�գ�");
			}
			if (userPassword == null || userPassword.equals("")) {
				sbf.append("���벻��Ϊ�գ�");
			}

			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("user/login.jsp").forward(request, response);
			} else {
				User user = us.login(userName, userPassword);
				HttpSession session = request.getSession();
				if (user != null) {
					if (user.getStatus() == 1) {
						session.setAttribute("userInfo", user);
						session.setAttribute("userName", userName);
						session.setAttribute("userId", user.getUser_id());
						request.getRequestDispatcher("index.jsp").forward(request, response);
					} else {
						request.setAttribute("msg", "�㻹û���");
						session.setAttribute("status", user.getStatus());
						request.getRequestDispatcher("user/login.jsp").forward(request, response);

					}

				} else {
					request.setAttribute("msg", "�û������������ ");

					request.getRequestDispatcher("user/login.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
