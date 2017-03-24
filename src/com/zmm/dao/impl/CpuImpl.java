/**
 * 
 */
package com.zmm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zmm.dao.ICpu;
import com.zmm.db.DataBase;
import com.zmm.dto.Cpu;

/**
 * @author Administrator
 *
 */
public class CpuImpl extends DataBase implements ICpu {

	Connection con = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.ICpu#addCpu(com.zmm.dto.Cpu)
	 */
	public Cpu getCpu(int id) throws Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Cpu c = null;
		try {
			con = getConn();
			ps = con.prepareStatement("select * from cpu where cpu_id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				c = new Cpu();
				c.setCpu_id(rs.getInt("cpu_id"));
				c.setCpu_name(rs.getString("cpu_name"));

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			rs.close();
			ps.close();

		}

		return c;
	}

	public boolean addCpu(Cpu c) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("insert into cpu(cpu_name) values(?)");
			ps.setString(1, c.getCpu_name());
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
	 * @see com.zmm.dao.ICpu#deleteCpu(int)
	 */

	public boolean deleteCpu(int id) throws Exception {
		PreparedStatement ps = null;
		try {

			ps = con.prepareStatement("delete from cpu where cpu_id=?");
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
	 * @see com.zmm.dao.ICpu#modifyCpu(com.zmm.dto.Cpu)
	 */

	public boolean modifyCpu(Cpu c) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("update cpu set cpu_name=? where cpu_id=?");
			ps.setString(1, c.getCpu_name());
			ps.setInt(2, c.getCpu_id());
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
	 * @see com.zmm.dao.ICpu#listCpu()
	 */

	public List<Cpu> listCpu() throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cpu c = null;
		List<Cpu> cList = null;
		try {

			ps = con.prepareStatement("select * from cpu ");
			rs = ps.executeQuery();
			cList = new ArrayList<Cpu>();
			while (rs.next()) {
				c = new Cpu();
				c.setCpu_id(rs.getInt("cpu_id"));
				c.setCpu_name(rs.getString("cpu_name"));
				cList.add(c);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			rs.close();
			ps.close();

		}
		return cList;
	}

	public void setConnection(Connection con) throws Exception {
		// TODO Auto-generated method stub
		this.con = con;
	}

}
