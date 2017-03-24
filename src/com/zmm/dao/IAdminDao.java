/**
 * 
 */
package com.zmm.dao;

import java.sql.Connection;
import java.util.List;

import com.zmm.dto.Admin;


/**
 * @author Administrator
 *
 */
public interface IAdminDao {

	public void setConnection(Connection con) throws Exception;

	/*
	 * 功能：管理员登录
	 */

	public Admin login(String adminName, String adminPassword) throws Exception;

	/*
	 * 获取电脑商品的个数
	 */
	public int getComputerCount() throws Exception;
	/*
	 * 获取管理员的个数
	 */
	public int getCount(Admin ad) throws Exception;

	/*
	 * 在回收站中删除管理员
	 */

	public boolean delete(int id) throws Exception;

	/*
	 * 添加管理员
	 */

	public boolean add(Admin ad) throws Exception;

	/*
	 * 找到管理员id
	 */

	public Admin getById(int id) throws Exception;

	/*
	 * 修改管理员信息
	 */
	public boolean update(Admin ad) throws Exception;

	/*
	 * 显示所有管理员
	 */
	public List<Admin> listAdmin(int pageNo,int pageSize) throws Exception;

	/*
	 * 删除管理员到回收站
	 */

	public boolean updateStatus(Admin ad) throws Exception;

	/*
	 * 查看回收站
	 */

	public List<Admin> recycleBinList(int pageNo,int pageSize) throws Exception;

	/*
	 * 清空回收站
	 */

	public boolean deleteStatus() throws Exception;

	
	
	
	/*
	 * 修改密码得到该密码
	 */
	
	public Admin getPassword(String adminPassword,String adminName)throws Exception;
	
}
