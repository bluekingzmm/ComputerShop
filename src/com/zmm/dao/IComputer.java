package com.zmm.dao;

import java.sql.Connection;
import java.util.List;


import com.zmm.dto.Computer;

public interface IComputer {
	/*
	 * 创建连接
	 */
	public void setConnection(Connection con) throws Exception;

	/*
	 * 添加电脑商品
	 */
	public boolean save(Computer com) throws Exception;
	
	/*
	 * 修改电脑信息
	 */
	public boolean update(Computer com) throws Exception;
	
	/*
	 * 修改电脑状态
	 */
	public boolean updateStatus(Computer com) throws Exception;
	
	/*
	 * 通过id获得电脑信息
	 */

	public Computer get(int comId) throws Exception;

	/*
	 * 显示电脑信息
	 */
	public List<Computer> list(int pageSize, int pageNo, String keyWord) throws Exception;

	/*
	 * 计算电脑信息个数
	 */
	public int getCount(String keyWord)throws Exception;


 
}
