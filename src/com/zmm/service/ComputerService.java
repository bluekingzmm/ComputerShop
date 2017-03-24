package com.zmm.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

import com.zmm.dao.IComputer;
import com.zmm.dao.impl.ComputerImpl;
import com.zmm.db.DataBase;

import com.zmm.dto.Computer;
import com.zmm.dto.ShopCart;

public class ComputerService {
	Connection con = null;
	IComputer com = new ComputerImpl();
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IComputer#save(com.zmm.dto.Computer)
	 */

	public boolean save(Computer c) throws Exception {
		boolean result = false;
		try {
			con = DataBase.getConn();
			com.setConnection(con);
			result = com.save(c);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(con);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IComputer#update(com.zmm.dto.Computer)
	 */

	public boolean update(Computer c) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			con = DataBase.getConn();
			com.setConnection(con);
			result = com.update(c);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(con);
		}
		return result;
	}

	/*
	 * ÐÞ¸Ä¿â´æ£¨¿â´æ=¿â´æ-¹ºÂòÊýÁ¿£©
	 */
	
	public void updateSubCount(HttpSession session) {
		// TODO Auto-generated method stub
		ShopCartService scs = null;
		ShopCart s = null;
		int count = 0;
		try {
			scs = new ShopCartService();
			List<ShopCart> sList = new ArrayList<ShopCart>(scs.getShopCart(session));
			for (int i = 0; i < sList.size(); i++) {
				s = sList.get(i);
				count = s.getCom().getNum() - s.getCount();
				Computer com=get(s.getCom().getCom_id());
				com.setNum(count);
				update(com);
			}
			
					} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	public boolean updateStatus(Computer c) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			con = DataBase.getConn();
			com.setConnection(con);
			result = com.updateStatus(c);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(con);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IComputer#get(int)
	 */

	public Computer get(int comId) throws Exception {
		Computer c = null;
		try {
			con = DataBase.getConn();
			com.setConnection(con);
			c = com.get(comId);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return c;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IComputer#list(int, int, java.lang.String)
	 */

	public List<Computer> list(int pageSize, int pageNo, String keyWord) throws Exception {
		// TODO Auto-generated method stub

		List<Computer> cList = null;

		try {
			con = DataBase.getConn();
			com.setConnection(con);
			cList = com.list(pageSize, pageNo, keyWord);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return cList;
	}

	public int getCount(String keyWord) throws Exception {
		// TODO Auto-generated method stub

		int count = 0;
		try {
			con = DataBase.getConn();
			com.setConnection(con);
			count = com.getCount(keyWord);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBase.releaseConnection(con);
		}
		return count;
	}

}
