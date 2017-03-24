/**
 * 
 */
package com.zmm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zmm.dao.IScreen;
import com.zmm.db.DataBase;
import com.zmm.dto.Screen;

/**
 * @author Administrator
 *
 */
public class ScreenImpl extends DataBase implements IScreen {

	Connection con=null;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IScreen#addScreen(com.zmm.dto.Screen)
	 */
	
	public boolean addScreen(Screen s) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("insert into screen(screen_name) values(?)");
			ps.setString(1, s.getScreen_name());
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
	 * @see com.zmm.dao.IScreen#deleteScreen(int)
	 */
	
	public boolean deleteScreen(int id) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from screen where screen_id=?");
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
	 * @see com.zmm.dao.IScreen#modifyScreen(com.zmm.dto.Screen)
	 */
	
	public boolean modifyScreen(Screen s) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("update screen set screen_name=? where screen_id=?");
			ps.setString(1, s.getScreen_name());
			ps.setInt(2, s.getScreen_id());
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
	 * @see com.zmm.dao.IScreen#listScreen()
	 */
	
	public List<Screen> listScreen() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Screen s = null;
		List<Screen> sList = null;
		try {
			ps = con.prepareStatement("select * from screen ");
			rs = ps.executeQuery();
			sList = new ArrayList<Screen>();
			while (rs.next()) {
				s = new Screen();
				s.setScreen_id(rs.getInt("screen_id"));
				s.setScreen_name(rs.getString("screen_name"));
				sList.add(s);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			rs.close();
			ps.close();
		}
		return sList;
	}

	
	public Screen getScreen(int id) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs=null;
		Screen s=null;
		try {

			ps = con.prepareStatement("select *  from screen where screen_id=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if (rs.next()) {
				s = new Screen();
				s.setScreen_id(rs.getInt("screen_id"));
				s.setScreen_name(rs.getString("screen_name"));
			
			}

		} catch (Exception e) {
			// TODO: handle exception
			DataBase.rollback();
		} finally {
			ps.close();
		}
		return s;
	}


	public void setConnection(Connection con) throws Exception {
		// TODO Auto-generated method stub
		this.con=con;
	}

}
