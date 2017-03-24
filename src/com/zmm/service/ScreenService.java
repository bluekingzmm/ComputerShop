package com.zmm.service;

import java.sql.Connection;
import java.util.List;

import com.zmm.dao.IScreen;
import com.zmm.dao.impl.ScreenImpl;
import com.zmm.db.DataBase;
import com.zmm.dto.Screen;

public class ScreenService {
	IScreen screen=new ScreenImpl();
	Connection con=null;
	public boolean addScreen(Screen s) throws Exception {

		boolean result = false;
		try {
			con = DataBase.getConn();
			screen.setConnection(con);
			result = screen.addScreen(s);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IBrand#deleteBrand(int)
	 */

	public boolean deleteScreen(int id) throws Exception {

		boolean result = false;
		try {
			con = DataBase.getConn();
			screen.setConnection(con);
			result = screen.deleteScreen(id);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IBrand#modifyBrand(com.zmm.dto.Brand)
	 */

	public boolean modifyScreen(Screen s) throws Exception {

		boolean result = false;
		try {
			con = DataBase.getConn();
			screen.setConnection(con);
			result = screen.modifyScreen(s);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IBrand#listBrand()
	 */

	public List<Screen> listScreen() throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		List<Screen> cList = null;
		try {
			con = DataBase.getConn();
			screen.setConnection(con);
			cList = screen.listScreen();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DataBase.releaseConnection(con);
		}

		return cList;
	}

	public Screen getScreen(int id) throws Exception {
		Connection con = null;
		Screen s = null;
		try {
			con = DataBase.getConn();
			screen.setConnection(con);
			s = screen.getScreen(id);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}

		return s;
	}
}
