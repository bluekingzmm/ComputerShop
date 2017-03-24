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
	 * ���ܣ�����cpu����
	 */
	public boolean addCpu(Cpu c) throws Exception;

	/*
	 * ���ܣ�ɾ��cpu����
	 */

	public boolean deleteCpu(int id) throws Exception;

	/*
	 * ���ܣ��޸�cpu����
	 */

	public boolean modifyCpu(Cpu c) throws Exception;

	/*
	 * ���ܣ�cpu�б�
	 */
	public List<Cpu> listCpu() throws Exception;
	
	/*
	 * ͨ��id�ҵ�
	 */
	
	public Cpu getCpu(int id) throws Exception;
}
