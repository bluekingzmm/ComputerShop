/**
 * 
 */
package com.zmm.service;

import java.sql.Connection;
import java.util.List;

import com.zmm.dao.IUser;
import com.zmm.dao.impl.UserImpl;
import com.zmm.db.DataBase;
import com.zmm.dto.User;


/**
 * @author Administrator
 *
 */
public class UserService {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IUser#add(com.zmm.dto.User)
	 */
	IUser user = new UserImpl();

	public boolean add(User u) throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		boolean result = false;
		try {
			con = DataBase.getConn();
			user.setConnection(con);
			result = user.add(u);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return result;

	}

	public List<User> search(String keyword) throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		List<User> uList=null;
		try {
			con = DataBase.getConn();
			user.setConnection(con);
			uList = user.search(keyword);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return uList;
		
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IUser#delete(int)
	 */
	public boolean delete(int userId) throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		boolean result = false;
		try {
			con = DataBase.getConn();
			user.setConnection(con);
			result = user.delete(userId);
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
	 * @see com.zmm.dao.IUser#modifyOwn(com.zmm.dto.User)
	 */
	public boolean modifyOwn(User u) throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		boolean result = false;
		try {
			con = DataBase.getConn();
			user.setConnection(con);
			result = user.modifyOwn(u);
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
	 * @see com.zmm.dao.IUser#getById(int)
	 */
	public User getById(int userId) throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		User u = null;
		try {
			con = DataBase.getConn();
			user.setConnection(con);
			u = user.getById(userId);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return u;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IUser#listUser(int, int)
	 */

	public List<User> listUser(int pageNo, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		List<User> uList = null;
		try {
			con = DataBase.getConn();
			user.setConnection(con);
			uList = user.listUser(pageNo, pageSize);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return uList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IUser#login(java.lang.String, java.lang.String)
	 */
	public User login(String userName, String userPassword) throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		User u = null;
		try {
			con = DataBase.getConn();
			user.setConnection(con);
			u = user.login(userName, userPassword);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return u;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IUser#getUserCount()
	 */
	public int getUserCount(User u) throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		int count = 0;
		try {
			con = DataBase.getConn();
			user.setConnection(con);
			count = user.getUserCount(u);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IUser#updateStatus(com.zmm.dto.Admin)
	 */
	public boolean updateStatus(User u) throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		boolean result = false;
		try {
			con = DataBase.getConn();
			user.setConnection(con);
			result = user.updateStatus(u);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return result;
	}
	public boolean updatePsw(String userPassword,String userName) throws Exception {
		// TODO Auto-generated method stub
		Connection con = null;
		boolean result = false;
		try {
			con = DataBase.getConn();
			user.setConnection(con);
			result = user.updatePsw(userPassword, userName);
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
	 * @see com.zmm.dao.IUser#recycleBinList(int, int)
	 */
	public List<User> recycleBinList(int pageNo, int pageSize,User u) throws Exception {
		Connection con = null;
		List<User> uList = null;
		try {
			con = DataBase.getConn();
			user.setConnection(con);
			uList = user.recycleBinList(pageNo, pageSize,u);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);

		}
		return uList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zmm.dao.IUser#deleteStatus()
	 */
	public boolean deleteStatus() throws Exception {
		Connection con = null;
		boolean result = false;
		try {
			con = DataBase.getConn();
			user.setConnection(con);
			user.deleteStatus();
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
	 * @see com.zmm.dao.IUser#getPassword(java.lang.String, java.lang.String)
	 */
	public User getPassword(String userPassword, String userName) throws Exception {
		Connection con = null;
		User u = null;
		try {
			con = DataBase.getConn();
			user.setConnection(con);
			u = user.getPassword(userPassword, userName);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}

		return u;
	}

	/*
	 * ²éÑ¯ÓÃÃÜÂë£¬ÅÐ¶ÏÊÇ·ñ×¢²á¹ý
	 */
	public boolean searchUsername(String userName) throws Exception {
		Connection con = null;
		boolean result=false;
		try {
			con = DataBase.getConn();
			user.setConnection(con);
			result = user.searchUsername(userName);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return result;
	}
	public User searchMail(String mail,String userName) throws Exception {
		Connection con = null;
		User u=null;
		try {
			con = DataBase.getConn();
			user.setConnection(con);
			u = user.searchMail(mail,userName);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return u;
	}
	public boolean searchMailName(String mail) throws Exception {
		Connection con = null;
		
		boolean result=false;
		try {
			con = DataBase.getConn();
			user.setConnection(con);
			result = user.searchMailName(mail);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return result;
	}
	/*
	 * ²éÑ¯×¢²áÂë£¬
	 */
	public User active(String code) throws Exception{
		Connection con = null;
		User u = null;
		try {
			con = DataBase.getConn();
			user.setConnection(con);
			u = user.active(code);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DataBase.releaseConnection(con);
		}
		return u;
	}
	
}
