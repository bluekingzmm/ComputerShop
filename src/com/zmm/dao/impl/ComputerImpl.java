/**
 * 
 */
package com.zmm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zmm.dao.IComputer;
import com.zmm.db.DataBase;
import com.zmm.dto.Brand;
import com.zmm.dto.Computer;
import com.zmm.dto.Cpu;
import com.zmm.dto.Screen;

/**
 * @author Administrator
 *
 */

public class ComputerImpl extends DataBase implements IComputer {

	Connection con = null;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IComputer#save(com.zmm.dto.Computer)
	 */

	public boolean save(Computer com) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(
					"insert into computer_info (brand_id,model,color,price,rel_price,img_path,reg_date,pixels,cpu_id,screen_id,ram,num,content) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, com.getBrand().getBrand_id());
			ps.setString(2, com.getModel());
			ps.setString(3, com.getColor());
			ps.setDouble(4, com.getPrice());
			ps.setDouble(5, com.getRel_price());
			ps.setString(6, com.getImgPath());
			java.sql.Date reg_date = (java.sql.Date) com.getReg_date();
			ps.setDate(7, reg_date);
			ps.setString(8, com.getPixels());
			ps.setInt(9, com.getCpu().getCpu_id());
			ps.setInt(10, com.getScreen().getScreen_id());
			ps.setString(11, com.getRam());
			ps.setInt(12, com.getNum());
			ps.setString(13, com.getContent());
			if (ps.executeUpdate() > 0) {
				DataBase.commit();
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			con.rollback();
		} finally {
			ps.close();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IComputer#update(com.zmm.dto.Computer)
	 */

	public boolean update(Computer com) throws Exception {
		// TODO Auto-generated method stub

		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(
					"update computer_info set brand_id=?,model=?,color=?,price=?,rel_price=?,img_path=?,reg_date=?,pixels=?,cpu_id=?,screen_id=?,ram=?,num=?,content=? where com_id=?");
			ps.setInt(1, com.getBrand().getBrand_id());
			ps.setString(2, com.getModel());
			ps.setString(3, com.getColor());
			ps.setDouble(4, com.getPrice());
			ps.setDouble(5, com.getRel_price());
			ps.setString(6, com.getImgPath());
			java.sql.Date reg_date = (java.sql.Date) com.getReg_date();
			ps.setDate(7, reg_date);
			ps.setString(8, com.getPixels());
			ps.setInt(9, com.getCpu().getCpu_id());
			ps.setInt(10, com.getScreen().getScreen_id());
			ps.setString(11, com.getRam());
			ps.setInt(12, com.getNum());
			ps.setString(13, com.getContent());
			ps.setInt(14, com.getCom_id());
			if (ps.executeUpdate() > 0) {
				DataBase.commit();
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		} finally {
			ps.close();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IComputer#get(int)
	 */

	public Computer get(int comId) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Computer com = null;
		Brand brand = null;
		Screen screen = null;
		Cpu cpu = null;
		try {
			ps = con.prepareStatement("select * from vcomputer where com_id=?");
			ps.setInt(1, comId);
			rs = ps.executeQuery();
			if (rs.next()) {
				brand = new Brand();
				brand.setBrand_id(rs.getInt("brand_id"));
				brand.setBrand_name(rs.getString("brand_name"));
				screen = new Screen();
				screen.setScreen_id(rs.getInt("screen_id"));
				screen.setScreen_name(rs.getString("screen_name"));
				cpu = new Cpu();
				cpu.setCpu_id(rs.getInt("cpu_id"));
				cpu.setCpu_name(rs.getString("cpu_name"));
				com = new Computer();
				com.setBrand(brand);
				com.setCpu(cpu);
				com.setScreen(screen);
				com.setColor(rs.getString("color"));
				com.setCom_id(rs.getInt("com_id"));
				com.setImgPath(rs.getString("img_path"));
				com.setModer(rs.getString("model"));
				com.setPixels(rs.getString("pixels"));
				com.setPrice(rs.getDouble("price"));
				com.setRel_price(rs.getInt("rel_price"));
				com.setRam(rs.getString("ram"));
				com.setReg_date(rs.getDate("reg_date"));
				com.setStatus(rs.getInt("status"));
				com.setNum(rs.getInt("num"));
				com.setContent(rs.getString("content"));
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			rs.close();
			ps.close();
		}
		return com;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IComputer#list(int, int, java.lang.String)
	 */

	public List<Computer> list(int pageSize, int pageNo, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		List<Computer> cList = null;
		ResultSet rs = null;
		Computer com = null;
		Brand brand = null;
		Screen screen = null;
		Cpu cpu = null;
		try {
			ps = con.prepareStatement("select * from vcomputer " + keyWord + "  limit ?,?");
			ps.setInt(1, (pageNo - 1) * pageSize);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			cList = new ArrayList<Computer>();
			while (rs.next()) {
				brand = new Brand();
				brand.setBrand_id(rs.getInt("brand_id"));
				brand.setBrand_name(rs.getString("brand_name"));

				screen = new Screen();
				screen.setScreen_id(rs.getInt("screen_id"));
				screen.setScreen_name(rs.getString("screen_name"));

				cpu = new Cpu();
				cpu.setCpu_id(rs.getInt("cpu_id"));
				cpu.setCpu_name(rs.getString("cpu_name"));

				com = new Computer();
				com.setBrand(brand);
				com.setCpu(cpu);
				com.setScreen(screen);
				com.setColor(rs.getString("color"));
				com.setCom_id(rs.getInt("com_id"));
				com.setImgPath(rs.getString("img_path"));
				com.setModer(rs.getString("model"));
				com.setPixels(rs.getString("pixels"));
				com.setPrice(rs.getDouble("price"));
				com.setRel_price(rs.getInt("rel_price"));
				com.setRam(rs.getString("ram"));
				com.setReg_date(rs.getDate("reg_date"));
				com.setStatus(rs.getInt("status"));
				com.setNum(rs.getInt("num"));
				com.setContent(rs.getString("content"));
				cList.add(com);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			rs.close();
			ps.close();
		}
		return cList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IComputer#getCount(java.lang.String)
	 */

	public int getCount(String keyWord) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = getConn();
			String sql = "select count(*) from vcomputer";
			pst = con.prepareStatement(sql + " " + keyWord);
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			rs.close();
			pst.close();
		}
		return count;
	}

	public void setConnection(Connection con) throws Exception {
		// TODO Auto-generated method stub
		this.con = con;
	}

	public boolean updateStatus(Computer com) throws Exception {

		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("update computer_info set reg_date=?,status=? where com_id=?");

			java.sql.Date reg_date = (java.sql.Date) com.getReg_date();
			ps.setDate(1, reg_date);
			ps.setInt(2, com.getStatus());
			ps.setInt(3, com.getCom_id());
			if (ps.executeUpdate() > 0) {
				DataBase.commit();
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			con.rollback();
		} finally {
			ps.close();
		}
		return false;
	}

}
