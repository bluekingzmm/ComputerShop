package com.zmm.service;

import java.sql.Connection;
import java.util.List;

import com.zmm.dao.IAdminDao;
import com.zmm.dao.impl.AdminImpl;
import com.zmm.db.DataBase;
import com.zmm.dto.Admin;

public class AdminService {

	IAdminDao admin = new AdminImpl();

	public Admin login(String adminName, String adminPassword) throws Exception {
		Connection con = null;
		Admin ad = null;
		try {
			con = DataBase.getConn();
			admin.setConnection(con);
			ad = admin.login(adminName, adminPassword);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return ad;

	}

	

	public Admin getById(int id) throws Exception {
		Connection con = null;
		Admin ad = null;
		try {
			con = DataBase.getConn();
			admin.setConnection(con);
			ad = admin.getById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DataBase.releaseConnection(con);
		}
		return ad;
	}

	public boolean add(Admin ad) throws Exception {
		Connection con = null;
		boolean result = false;
		try {
			con = DataBase.getConn();
			admin.setConnection(con);
			result = admin.add(ad);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DataBase.releaseConnection(con);
		}
		return result;
	}

	public boolean delete(int id) throws Exception {
		Connection con = null;
		boolean result = false;
		try {
			con = DataBase.getConn();
			admin.setConnection(con);
			result = admin.delete(id);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DataBase.releaseConnection(con);
		}
		return result;
	}


	public boolean update(Admin ad) throws Exception {
		Connection con = null;
		boolean result = false;
		try {
			con = DataBase.getConn();
			admin.setConnection(con);
			result = admin.update(ad);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DataBase.releaseConnection(con);
		}
		return result;
	}

	public List<Admin> listAdmin(int pageNo,int pageSize) throws Exception {

		Connection con = null;
		List<Admin> aList = null;
		try {
			con = DataBase.getConn();
			admin.setConnection(con);
			aList = admin.listAdmin(pageNo, pageSize);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return aList;

	}

	public boolean updateStatus(Admin ad) throws Exception {
		
		Connection con = null;
		boolean result = false;
		try {
			con = DataBase.getConn();
			admin.setConnection(con);
			result = admin.updateStatus(ad);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DataBase.releaseConnection(con);
		}
		return result;
	}

	public boolean deleteStatus() throws Exception {
		Connection con = null;
		boolean result = false;
		try {
			con = DataBase.getConn();
			admin.setConnection(con);
			admin.deleteStatus();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DataBase.releaseConnection(con);
		}
		return result;

	}

	public List<Admin> recycleBinList(int pageNo,int pageSize) throws Exception {
		Connection con = null;
		List<Admin> rList = null;
		try {
			con = DataBase.getConn();
			admin.setConnection(con);
			rList = admin.recycleBinList(pageNo, pageSize);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);

		}
		return rList;
	}

	public Admin getPassword(String adminPassword, String adminName) throws Exception{
		Connection con = null;
		Admin ad=null;
		try {
			con = DataBase.getConn();
			admin.setConnection(con);
			ad=admin.getPassword(adminPassword, adminName);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
	
		return ad;
	}
	public int getCount(Admin  ad) throws Exception {
		Connection con = null;
		int count=0;
		try {
			con = DataBase.getConn();
			admin.setConnection(con);
			count=admin.getCount(ad);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return count;
	}
	
}
