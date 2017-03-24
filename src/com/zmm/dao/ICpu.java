/**
 * 
 */
package com.zmm.dao;

import java.sql.Connection;
import java.util.List;

import com.zmm.dto.Cpu;

/**
 * @author Administrator
 *
 */
public interface ICpu {
	
	public void setConnection(Connection con) throws Exception;
	/*
	 * 功能：增加cpu名称
	 */
	public boolean addCpu(Cpu c) throws Exception;

	/*
	 * 功能：删除cpu名称
	 */

	public boolean deleteCpu(int id) throws Exception;

	/*
	 * 功能：修改cpu名称
	 */

	public boolean modifyCpu(Cpu c) throws Exception;

	/*
	 * 功能：cpu列表
	 */
	public List<Cpu> listCpu() throws Exception;
	
	/*
	 * 通过id找到
	 */
	
	public Cpu getCpu(int id) throws Exception;
}
