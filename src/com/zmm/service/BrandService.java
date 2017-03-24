package com.zmm.service;

import java.sql.Connection;

import java.util.List;

import com.zmm.dao.IBrand;
import com.zmm.dao.impl.BrandImpl;
import com.zmm.db.DataBase;
import com.zmm.dto.Brand;

public class BrandService {
	Connection con = null;
	IBrand brand = new BrandImpl();

	public boolean addBrand(Brand b) throws Exception {

		boolean result = false;
		try {
			con = DataBase.getConn();
			brand.setConnection(con);
			result = brand.addBrand(b);
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

	public boolean deleteBrand(int id) throws Exception {

		boolean result = false;
		try {
			con = DataBase.getConn();
			brand.setConnection(con);
			result = brand.deleteBrand(id);
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

	public boolean modifyBrand(Brand b) throws Exception {

		boolean result = false;
		try {
			con = DataBase.getConn();
			brand.setConnection(con);
			result = brand.modifyBrand(b);
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

	public List<Brand> listBrand() throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		List<Brand> bList = null;
		try {
			con = DataBase.getConn();
			brand.setConnection(con);
			bList = brand.listBrand();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DataBase.releaseConnection(con);
		}

		return bList;
	}

	public Brand getBrand(int id) throws Exception {
		Connection con = null;
		Brand b = null;
		try {
			con = DataBase.getConn();
			brand.setConnection(con);
			b = brand.getBrand(id);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}

		return b;
	}

}
