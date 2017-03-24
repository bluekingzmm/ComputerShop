package com.zmm.service;

import java.sql.Connection;
import java.util.List;
import com.zmm.dao.ICpu;
import com.zmm.dao.impl.CpuImpl;
import com.zmm.db.DataBase;
import com.zmm.dto.Cpu;

public class CpuService {
	Connection con = null;
	ICpu cpu = new CpuImpl();

	public boolean addCpu(Cpu c) throws Exception {

		boolean result = false;
		try {
			con = DataBase.getConn();
			cpu.setConnection(con);
			result = cpu.addCpu(c);
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

	public boolean deleteCpu(int id) throws Exception {

		boolean result = false;
		try {
			con = DataBase.getConn();
			cpu.setConnection(con);
			result = cpu.deleteCpu(id);
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

	public boolean modifyCpu(Cpu c) throws Exception {

		boolean result = false;
		try {
			con = DataBase.getConn();
			cpu.setConnection(con);
			result = cpu.modifyCpu(c);
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

	public List<Cpu> listCpu() throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		List<Cpu> cList = null;
		try {
			con = DataBase.getConn();
			cpu.setConnection(con);
			cList = cpu.listCpu();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DataBase.releaseConnection(con);
		}

		return cList;
	}

	public Cpu getCpu(int id) throws Exception {
		Connection con = null;
		Cpu c = null;
		try {
			con = DataBase.getConn();
			cpu.setConnection(con);
			c = cpu.getCpu(id);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}

		return c;
	}

}
