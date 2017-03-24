package com.zmm.dao;

import java.sql.Connection;
import java.util.List;

import com.zmm.dto.User;

public interface IUser {
	
	public void setConnection(Connection con) throws Exception;

	/*
	 * 用户注册
	 */
	public boolean add(User user) throws Exception;

	/*
	 * 删除用户
	 */

	public boolean delete(int userId) throws Exception;

	/*
	 * 用户修改个人信息
	 */
	public boolean modifyOwn(User user) throws Exception;

	/*
	 * 通过id找用户
	 */

	public User getById(int userId) throws Exception;
	
	/*
	 * 显示用户列表
	 */
	
	public List<User> listUser(int pageNo,int pageSize)throws Exception;
	
	/*
	 * 用户登录
	 */
	
	public User login(String userName, String userPassword) throws Exception;
	

	/*
	 * 获取用户的个数
	 */
	public int getUserCount(User user) throws Exception;
	
	/*
	 * 删除用户到回收站
	 */

	public boolean updateStatus(User user) throws Exception;

	/*
	 * 查看回收站
	 */

	public List<User> recycleBinList(int pageNo,int pageSize,User u) throws Exception;

	/*
	 * 清空回收站
	 */

	public boolean deleteStatus() throws Exception;

	
	/*
	 * 按照关键字查询
	 */
	
	public List<User> search(String keyword) throws Exception;
	
	/*
	 * 修改密码得到该密码
	 */
	
	public User getPassword(String userPassword,String userName)throws Exception;
	
	/*
	 * 查询用密码，判断是否注册过
	 */
	public boolean searchUsername(String userName)throws Exception;
	
	/*
	 * 查询注册码
	 */
	
	
	public User active(String code) throws Exception;
	
	/*
	 * 通过邮箱查询是否激活了
	 */
	public User searchMail(String mail,String userName) throws Exception;
	
	public boolean updatePsw(String userPassword,String userName) throws Exception;
	
	public boolean searchMailName(String mail) throws Exception;
}
