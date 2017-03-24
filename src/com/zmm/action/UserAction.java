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
		String u = request.getParameter("userName");// 获取用户名
		try {
			session.setAttribute("UserName", u);
		
			request.getRequestDispatcher("user/modifyPsw/forgetPwd5.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * 修改密码时发送邮箱
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
				sbf.append("邮箱不能为空！");
			}
			if (ue == null || ue.equals("")) {
				sbf.append("用户名不能为空！");
			}

			user = us.searchMail(mail, ue);// 按照邮箱找到用户

			if (user != null) {
				s.setAttribute("userName", ue);

				if (sbf.length() != 0) {
					request.setAttribute("msg", sbf.toString());
					request.getRequestDispatcher("user/login.jsp").forward(request, response);
				} else {
					
						Properties props = new Properties();
						// 对于类进行调用配置资源的文件数据this.getClass().getClassLoader().getResourceAsStream
						props.load(this.getClass().getClassLoader().getResourceAsStream("send.properties"));
						String host = props.getProperty("host");// 获取服务器主机
						String userName = props.getProperty("userName");//
						// 获取用户名
						String userPassword = props.getProperty("userPassword");// 获取密码
						
						String from = props.getProperty("from");// 获取发件人
						String to = user.getMail();// 获取收件人
						String subject = props.getProperty("subject");// 获取主题
//						s.setAttribute("userName", ue);
//						String content = "<a href='http://10.101.32.192:8080/ComputerShop/UserAction?operate=pMail&userName={0}'>点击进入修改页面</a>";
						String content=props.getProperty("content");
						content = MessageFormat.format(content, ue);
						Session session = MailUtils.createSession(host, userName, userPassword);// 得到session


						Mail email = new Mail(from, to, subject, content);
						// 创建邮件对象

						MailUtils.send(session, email);// 发邮件！

						request.setAttribute("msg", "请马上到邮箱查看信息");
						request.getRequestDispatcher("user/login.jsp").forward(request, response);
					}

				}
//			} else {
//				request.setAttribute("msg", "邮箱不对，请重试！");
//				request.getRequestDispatcher("user/login.jsp").forward(request, response);
//
//			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	// 前台用户取消订单
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

	// 后台显示未激活的用户
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
	 * 后台按照用户名进行查询
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
	 * 前台用户未激活时发送激活码
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
				sbf.append("邮箱不能为空！");
			}
			if (ue == null || ue.equals("")) {
				sbf.append("用户名不能为空！");
			}

			user = us.searchMail(mail, ue);// 按照邮箱找到用户

			if (user != null) {
				if (sbf.length() != 0) {
					request.setAttribute("msg", sbf.toString());
					request.getRequestDispatcher("user/mail.jsp").forward(request, response);
				} else {
					user.setCode(Uuid.uuid());// 激活码
					user.setStatus(1);// 设置激活，用户存在状态设置为1
					if (us.modifyOwn(user)) {
						Properties props = new Properties();
						// 对于类进行调用配置资源的文件数据this.getClass().getClassLoader().getResourceAsStream
						props.load(this.getClass().getClassLoader().getResourceAsStream("email.properties"));
						String host = props.getProperty("host");// 获取服务器主机
						String userName = props.getProperty("userName");//
						// 获取用户名
						String userPassword = props.getProperty("userPassword");// 获取密码

						String from = props.getProperty("from");// 获取发件人
						String to = user.getMail();// 获取收件人
						String subject = props.getProperty("subject");// 获取主题
						String content = props.getProperty("content"); // 获取邮件内容

						// 字符串模版参数替换
						content = MessageFormat.format(content, user.getCode());// 替换配置文件中的{0}
																				// content
																				// 中需要被替换的就是{}中的参数

						Session session = MailUtils.createSession(host, userName, userPassword);// 得到session

						Mail email = new Mail(from, to, subject, content);
						// 创建邮件对象

						MailUtils.send(session, email);// 发邮件！

						request.setAttribute("msg", "请马上到邮箱激活");
						request.getRequestDispatcher("user/login.jsp").forward(request, response);
					}

				}
			} else {
				request.setAttribute("msg", "邮箱不对，请重试！");
				request.getRequestDispatcher("user/login.jsp").forward(request, response);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// 前台显示自己的信息
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
	 * 前台修改用户密码（完全忘记密码）第三个步骤（设置新的密码）
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
				sbf.append("新密码不能为空！");
			}
			if (againPsw == null || againPsw.equals("")) {
				sbf.append("请再次输入密码！");
			}
			if (!newPsw.equals(againPsw)) {
				sbf.append("两次输入密码不同！");
			}
			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("user/modifyPsw/forgetPwd3.jsp").forward(request, response);
			} else {
				if (us.updatePsw(newPsw, userName)) {
					request.setAttribute("msg", "修改密码成功！");
					request.getRequestDispatcher("user/modifyPsw/forgetPwd4.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "修改密码失败");
					request.getRequestDispatcher("user/modifyPsw/forgetPwd3.jsp").forward(request, response);

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/*
	 * 前台修改用户密码（完全忘记密码）第二个步骤（先由用户输入邮箱地址查询，匹配数据库用户先前注册邮箱是否正确）
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
				sbf.append("邮箱不能为空！");
			}
			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("user/modifyPsw/forgetPwd2.jsp").forward(request, response);
			} else {
				if (us.searchMailName(mail)) {
					request.getRequestDispatcher("user/modifyPsw/forgetPwd3.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "邮箱不正确！");
					request.getRequestDispatcher("user/modifyPsw/forgetPwd2.jsp").forward(request, response);

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/*
	 * 前台修改用户密码（完全忘记密码）第一个步骤（先由用户输入用户名，核实用户是否注册过）
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
				sbf.append("用户名不能为空！");
			}
			if (checkCode == null || checkCode.equals("")) {
				sbf.append("验证码不能空！");
			}
			us = new UserService();
			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("user/modifyPsw/index.jsp").forward(request, response);
			} else {
				if (us.searchUsername(userName)) {
					if (!checkCode.equalsIgnoreCase((String) session.getAttribute("randCheckCode"))) {
						request.setAttribute("msg", "验证码错误");
						request.getRequestDispatcher("user/modifyPsw/index.jsp").forward(request, response);
					} else {
						session.setAttribute("userName", userName);
						request.getRequestDispatcher("user/modifyPsw/forgetPwd2.jsp").forward(request, response);

					}

				} else {
					request.setAttribute("msg", "用户名不存在！");
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
		r = s + random.nextInt(e - s); // 随机生成RGB颜色中的r值
		g = s + random.nextInt(e - s); // 随机生成RGB颜色中的g值
		b = s + random.nextInt(e - s); // 随机生成RGB颜色中的b值
		return new Color(r, g, b);
	}

	public void yzm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置不缓存图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// 指定生成的响应图片,一定不能缺少这句话,否则错误.
		response.setContentType("image/jpeg");
		int width = 112, height = 37; // 指定生成验证码的宽度和高度
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // 创建BufferedImage对象,其作用相当于一图片
		Graphics g = image.getGraphics(); // 创建Graphics对象,其作用相当于画笔
		Graphics2D g2d = (Graphics2D) g; // 创建Grapchics2D对象
		Random random = new Random();
		Font mfont = new Font("楷体", Font.BOLD, 24); // 定义字体样式
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height); // 绘制背景
		g.setFont(mfont); // 设置字体
		g.setColor(getRandColor(180, 200));

		// 绘制100条颜色和位置全部为随机产生的线条,该线条为2f
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int x1 = random.nextInt(6) + 1;
			int y1 = random.nextInt(12) + 1;
			BasicStroke bs = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL); // 定制线条样式
			Line2D line = new Line2D.Double(x, y, x + x1, y + y1);
			g2d.setStroke(bs);
			g2d.draw(line); // 绘制直线
		}
		// 输出由英文，数字，和中文随机组成的验证文字，具体的组合方式根据生成随机数确定。
		String sRand = "";
		String ctmp = "";
		int itmp = 0;
		// 制定输出的验证码为四位
		for (int i = 0; i < 4; i++) {
			switch (random.nextInt(3)) {
			case 1: // 生成A-Z的字母
				itmp = random.nextInt(26) + 65;
				ctmp = String.valueOf((char) itmp);
				break;
			case 2: // 生成汉字
				String[] rBase = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
				// 生成第一位区码
				int r1 = random.nextInt(3) + 11;
				String str_r1 = rBase[r1];
				// 生成第二位区码
				int r2;
				if (r1 == 13) {
					r2 = random.nextInt(7);
				} else {
					r2 = random.nextInt(16);
				}
				String str_r2 = rBase[r2];
				// 生成第一位位码
				int r3 = random.nextInt(6) + 10;
				String str_r3 = rBase[r3];
				// 生成第二位位码
				int r4;
				if (r3 == 10) {
					r4 = random.nextInt(15) + 1;
				} else if (r3 == 15) {
					r4 = random.nextInt(15);
				} else {
					r4 = random.nextInt(16);
				}
				String str_r4 = rBase[r4];
				// 将生成的机内码转换为汉字
				byte[] bytes = new byte[2];
				// 将生成的区码保存到字节数组的第一个元素中
				String str_12 = str_r1 + str_r2;
				int tempLow = Integer.parseInt(str_12, 16);
				bytes[0] = (byte) tempLow;
				// 将生成的位码保存到字节数组的第二个元素中
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
			// 将生成的随机数进行随机缩放并旋转制定角度 PS.建议不要对文字进行缩放与旋转,因为这样图片可能不正常显示
			/* 将文字旋转制定角度 */
			Graphics2D g2d_word = (Graphics2D) g;
			AffineTransform trans = new AffineTransform();
			trans.rotate((45) * 3.14 / 180, 15 * i + 8, 7);
			/* 缩放文字 */
			float scaleSize = random.nextFloat() + 0.8f;
			if (scaleSize > 1f)
				scaleSize = 1f;
			trans.scale(scaleSize, scaleSize);
			g2d_word.setTransform(trans);
			g.drawString(ctmp, 15 * i + 18, 14);
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("randCheckCode", sRand);
		g.dispose(); // 释放g所占用的系统资源
		ImageIO.write(image, "JPEG", response.getOutputStream()); // 输出图片
	}

	/*
	 * 前台修改用户密码（登录过改密码）自动获取原密码）
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
				sbf.append("原密码不能为空!<br/>");
			}
			user = us.getPassword(oldPsw, userName);

			if (user != null) {

				if (newPsw == null || newPsw.equals("")) {
					sbf.append("新密码不能为空！<br/>");
				}
				if (againPsw == null || againPsw.equals("")) {
					sbf.append("再次输入新密码不能为空！<br/>");
				}
				if (oldPsw.equals(newPsw)) {
					sbf.append("新密码不能原来密码相同！<br/>");
				}
				if (!newPsw.equals(againPsw)) {
					sbf.append("两次输入密码不同！<br/>");
				}
			} else {
				sbf.append("原密码输入错误！<br/>");
			}

			if (sbf.length() != 0) {
				request.setAttribute("msg", sbf.toString());
				request.getRequestDispatcher("user/modifyPsw.jsp").forward(request, response);
			} else {
				if (us.updatePsw(newPsw, userName)) {
					request.setAttribute("msg", "修改密码成功！");
					request.getRequestDispatcher("user/login.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "修改密码失败");
					request.getRequestDispatcher("user/modifyPsw.jsp").forward(request, response);

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// 激活用户
	private void activate(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		User user = null;
		UserService us = null;
		String code = request.getParameter("code");// 获取激活码
		try {
			us = new UserService();
			user = us.active(code);// 查询激活码是否存在
			if (user == null) {
				request.setAttribute("msg", "激活码无效");
			} else if (user.getStatus() == 1) {
				request.setAttribute("msg", "你已经激活过了！");
			} else {
				user.setStatus(1);
				us.updateStatus(user);
				request.setAttribute("msg", "激活成功！请登录");

			}
			request.getRequestDispatcher("user/register.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// 用户退出
	private void quit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		try {
			if (session != null) {
				session.invalidate();// 删除session数据（session失效）
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// 后台还原用户（从回收站中还原用户）
	private void recover(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		UserService us = null;
		User user = null;
		try {
			us = new UserService();
			user = us.getById(id);
			if (user != null) {
				user.setStatus(1);// 将用户状态改变（存在为1）
				if (us.updateStatus(user)) {
					request.setAttribute("msg", "还原成功！");
					list(request, response);
				} else {
					request.setAttribute("msg", "还原失败！！");
					recycleBinList(request, response);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// 将用户删除到回收站
	private void recycleBin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		UserService us = null;
		User user = null;
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			us = new UserService();
			user = us.getById(id);
			user.setStatus(-1);// 设置用户状态为-1
			if (user != null) {
				if (us.updateStatus(user)) {
					request.setAttribute("msg", "删除成功！");
					list(request, response);
				} else {
					request.setAttribute("msg", "删除失败！");
					list(request, response);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// 显示回收站的列表
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
			int totalPage = (int) Math.ceil((double) count / pageSize);// 得到总页数
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

	// 显示用户列表
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

	// 查看自己信息
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
				sbf.append("邮箱不能为空！");
			} else {
				user.setMail(mail);
			}
			if (relName == null || relName.equals("")) {
				sbf.append("真实姓名不能为空！");
			} else {
				user.setRel_name(relName);
			}
			if (Id == null || Id.equals("")) {
				sbf.append("身份证号码不能为空！");
			} else {
				user.setId(Id);
			}

			if (phone == null || phone.equals("")) {
				sbf.append("手机号码不能为空！");
			} else {
				user.setPhone(phone);
			}

			if (address == null || address.equals("")) {
				sbf.append("地址不能为空！");
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
					request.setAttribute("msg", "修改成功！");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "修改失败！！");
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
					request.setAttribute("msg", "删除成功！");
					list(request, response);
				} else {
					request.setAttribute("msg", "删除失败！！");
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
				sbf.append("用户名不能为空！");
			} else {
				user.setUser_name(userName);
			}
			if (userPassword == null || userPassword.equals("")) {
				sbf.append("密码不能为空！");
			} else {
				user.setUser_password(userPassword);
			}
			if (address == null || address.equals("")) {
				sbf.append("收货地址不能为空！");
			} else {
				user.setAddress(address);

			}
			if (phone == null || phone.equals("")) {
				sbf.append("手机号不能为空！");
			} else {
				user.setPhone(phone);
			}
			if (Id == null || Id.equals("")) {
				sbf.append("身份证号不能为空！");
			} else {
				user.setId(Id);
			}
			if (relName == null || relName.equals("")) {
				sbf.append("真实姓名不能为空！");
			} else {
				user.setRel_name(relName);
			}
			if (mail == null || mail.equals("")) {
				sbf.append("邮箱不能为空！");
			} else {
				user.setMail(mail);
			}
			if (sex.equals("男")) {
				user.setSex(sex);
			} else {
				user.setSex("女");
			}
			if (agree.equals("no")) {
				sbf.append("请同意本站条约才能注册！");
			}
			// 日期格式工具
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();// 得到系统当前时间
			String d = dateFormat.format(date);// 将系统当前时间格式化
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

					request.setAttribute("msg", "用户名已经被注册了，请重新注册！");
					request.getRequestDispatcher("user/register.jsp").forward(request, response);

				} else {
					if (us.add(user)) {
						/*
						 * 发邮件 布置配置文件！
						 */
						// 获取配置文件内容

						Properties props = new Properties();
						props.load(this.getClass().getClassLoader().getResourceAsStream("email.properties"));
						String host = props.getProperty("host");// 获取服务器主机
						String name = props.getProperty("userName");//
						// 获取用户名
						String password = props.getProperty("userPassword");// 获取密码

						String from = props.getProperty("from");// 获取发件人
						String to = user.getMail();// 获取收件人
						String subject = props.getProperty("subject");// 获取主题
						String content = props.getProperty("content");//
						// 获取邮件内容
						content = MessageFormat.format(content, user.getCode());// 替换配置文件中的{0}

						Session session = MailUtils.createSession(host, name, password);// 得到session

						Mail email = new Mail(from, to, subject, content);//
						// 创建邮件对象
						try {
							MailUtils.send(session, email);// 发邮件！
						} catch (MessagingException e) {
						}
						request.setAttribute("msg", "恭喜，注册成功！请马上到邮箱激活");
						request.getRequestDispatcher("user/register.jsp").forward(request, response);
					} else {
						request.setAttribute("msg", "注册失败！");
						request.getRequestDispatcher("user/register.jsp").forward(request, response);
					}
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Id截取
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

	// 当前时间截取
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
				sbf.append("用户名不能为空！");
			}
			if (userPassword == null || userPassword.equals("")) {
				sbf.append("密码不能为空！");
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
						request.setAttribute("msg", "你还没激活！");
						session.setAttribute("status", user.getStatus());
						request.getRequestDispatcher("user/login.jsp").forward(request, response);

					}

				} else {
					request.setAttribute("msg", "用户名或密码错误 ");

					request.getRequestDispatcher("user/login.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
