/**
 * 
 */
package com.zmm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zmm.dao.IAdminDao;
import com.zmm.db.DataBase;
import com.zmm.dto.Admin;

/**
 * @author Administrator
 *
 */
public class AdminImpl implements IAdminDao {
	Connection con = null;

	/*
	 * (non-Javadoc)
	 * 
	 * .
	 * 
	 * @see com.zmm.dao.IAdminDao#deleteAdmin(int)
	 */
	public void setConnection(Connection con) throws Exception {
		// TODO Auto-generated method stub
		this.con = con;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IAdminDao#login(java.lang.String, java.lang.String)
	 */

	public Admin login(String adminName, String adminPassword) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Admin ad = null;
		try {
			ps = con.prepareStatement("select * from admin where admin_name=? and admin_password=?");
			ps.setString(1, adminName);
			ps.setString(2, adminPassword);
			rs = ps.executeQuery();
			if (rs.next()) {
				ad = new Admin();
				ad.setAdmin_name(rs.getString("admin_name"));
				ad.setAdmin_password(rs.getString("admin_password"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			rs.close();
			ps.close();
		}
		return ad;
	}

	

	public int getCount(Admin ad) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(*) from admin where status=?";
		
			ps = con.prepareStatement(sql);
			ps.setInt(1, ad.getStatus());
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			rs.close();
			ps.close();
		}
		return count;
	}


	public int getComputerCount() throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(*) from computer_shop";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			rs.close();
			ps.close();
		}
		return count;
	}

	public boolean delete(int id) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from admin where id=?");
			ps.setInt(1, id);
			if (ps.executeUpdate() > 0) {
				DataBase.commit();
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			DataBase.rollback();

		}finally {
			ps.close();
		}
		return false;
	}

	public boolean add(Admin ad) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("insert into admin (admin_name,admin_password) values (?,?)");
			ps.setString(1, ad.getAdmin_name());
			ps.setString(2, ad.getAdmin_password());
			if (ps.executeUpdate() > 0) {
				DataBase.commit();
				return true;
			}
		} catch (Exception e) {
			DataBase.rollback();
		} finally {
			ps.close();
		}
		return false;
	}

	public Admin getById(int id) throws Exception {
		// TODO Auto-generated method stub
		Admin ad = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = con.prepareStatement("select * from admin where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				ad = new Admin();
				ad.setId(rs.getInt("id"));
				ad.setAdmin_name(rs.getString("admin_name"));
				ad.setAdmin_password(rs.getString("admin_password"));
				ad.setStatus(rs.getInt("status"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			rs.close();
			ps.close();
		}
		return ad;
	}

	public boolean update(Admin ad) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("update admin set admin_name=? where id=?");
			ps.setString(1, ad.getAdmin_name());
			ps.setInt(2, ad.getId());
			if (ps.executeUpdate() > 0) {
				DataBase.commit();
				return true;
			}
		} catch (Exception e) {
			DataBase.rollback();
		} finally {
			ps.close();
		}
		return false;
	}

	public List<Admin> listAdmin(int pageNo,int pageSize) throws Exception {
		// TODO Auto-generated method stub
		List<Admin> aList = null;
		Admin ad = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from admin where status=? limit ?,?");
			ps.setInt(1, 1);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			aList = new ArrayList<Admin>();
			while (rs.next()) {
				ad = new Admin();
				ad.setId(rs.getInt("id"));
				ad.setAdmin_name(rs.getString("admin_name"));
				ad.setAdmin_password(rs.getString("admin_password"));
				ad.setStatus(rs.getInt("status"));
				aList.add(ad);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			rs.close();
			ps.close();
		}
		return aList;
	}

	public boolean updateStatus(Admin ad) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("update admin set status=? where id=?");
			ps.setInt(1, ad.getStatus());
			ps.setInt(2, ad.getId());
			if (ps.executeUpdate() > 0) {
				DataBase.commit();
				return true;
			}

		} catch (Exception e) {
			DataBase.rollback();
		} finally {
			ps.close();
		}

		return false;
	}

	public List<Admin> recycleBinList(int pageNo,int pageSize) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		List<Admin> aList = null;
		Admin ad = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from admin where status=? limit ?,?");
			ps.setInt(1, 0);
			ps.setInt(2, (pageNo-1)*pageSize);
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			aList = new ArrayList<Admin>();
			while (rs.next()) {
				ad = new Admin();
				ad.setAdmin_name(rs.getString("admin_name"));
				ad.setAdmin_password(rs.getString("admin_password"));
				ad.setId(rs.getInt("id"));
				ad.setStatus(rs.getInt("status"));
				aList.add(ad);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			rs.close();
			ps.close();
		}
		return aList;
	}

	public boolean deleteStatus() throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from admin where status=?");
			ps.setInt(1, 0);
			if (ps.executeUpdate() > 0) {
				DataBase.commit();
				return true;
			}

		} catch (Exception e) {
			DataBase.rollback();
		} finally {
			ps.close();
		}
		return false;
	}

	public Admin getPassword(String adminPassword,String adminName) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		Admin ad=null;
		ResultSet rs=null;
		try {
			ps=con.prepareStatement("select * from admin where admin_name=? and admin_password=?");
			ps.setString(1, adminName);
			ps.setString(2, adminPassword);
			rs=ps.executeQuery();
			if(rs.next()){
				ad=new Admin();
				ad.setAdmin_password(rs.getString("admin_password"));
				ad.setAdmin_name(rs.getString("admin_name"));
				ad.setId(rs.getInt("id"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			rs.close();
			ps.close();
		}
		return ad;
	}



}
