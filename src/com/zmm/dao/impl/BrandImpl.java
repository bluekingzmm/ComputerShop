/**
 * 
 */
package com.zmm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zmm.dao.IBrand;
import com.zmm.db.DataBase;
import com.zmm.dto.Brand;

/**
 * @author Administrator
 *
 */
public class BrandImpl extends DataBase implements IBrand {

	Connection con=null;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IBrand#addBrand(com.zmm.dto.Brand)
	 */
	
	public boolean addBrand(Brand b) throws Exception {
		
		PreparedStatement ps = null;
		try {
		
			ps = con.prepareStatement("insert into brand(brand_name) values(?)");
			ps.setString(1, b.getBrand_name());
			if (ps.executeUpdate() > 0) {
				DataBase.commit();
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			DataBase.rollback();
		} finally {
			ps.close();
			
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IBrand#deleteBrand(int)
	 */
	
	public boolean deleteBrand(int id) throws Exception {
		PreparedStatement ps = null;
		try {

			ps = con.prepareStatement("delete from brand where brand_id=?");
			ps.setInt(1, id);
			if (ps.executeUpdate() > 0) {
				DataBase.commit();
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			DataBase.rollback();
		} finally {
			ps.close();
		
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IBrand#modifyBrand(com.zmm.dto.Brand)
	 */
	
	public boolean modifyBrand(Brand b) throws Exception {
	
		PreparedStatement ps = null;
		try {

			ps = con.prepareStatement("update brand set brand_name=? where brand_id=?");
			ps.setString(1, b.getBrand_name());
			ps.setInt(2, b.getBrand_id());
			if (ps.executeUpdate() > 0) {
				DataBase.commit();
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			DataBase.rollback();
		} finally {
			ps.close();
		
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IBrand#listBrand()
	 */

	public List<Brand> listBrand() throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Brand b = null;
		List<Brand> bList = null;
		try {
			con = getConn();
			ps = con.prepareStatement("select * from brand ");
			rs = ps.executeQuery();
			bList = new ArrayList<Brand>();
			while (rs.next()) {
				b = new Brand();
				b.setBrand_id(rs.getInt("brand_id"));
				b.setBrand_name(rs.getString("brand_name"));
				bList.add(b);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			rs.close();
			ps.close();
			
		}
		return bList;
	}

	public Brand getBrand(int id) throws Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Brand b = null;
		try {
			con = getConn();
			ps = con.prepareStatement("select * from brand where brand_id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				b = new Brand();
				b.setBrand_id(rs.getInt("brand_id"));
				b.setBrand_name(rs.getString("brand_name"));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			rs.close();
			ps.close();
			
		}
		
		return b;
	}


	public void setConnection(Connection con) throws Exception {
		// TODO Auto-generated method stub
		this.con=con;
	}

}
