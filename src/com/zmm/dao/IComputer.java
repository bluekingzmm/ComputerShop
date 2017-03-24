package com.zmm.dao;

import java.sql.Connection;
import java.util.List;


import com.zmm.dto.Computer;

public interface IComputer {
	/*
	 * ��������
	 */
	public void setConnection(Connection con) throws Exception;

	/*
	 * ���ӵ�����Ʒ
	 */
	public boolean save(Computer com) throws Exception;
	
	/*
	 * �޸ĵ�����Ϣ
	 */
	public boolean update(Computer com) throws Exception;
	
	/*
	 * �޸ĵ���״̬
	 */
	public boolean updateStatus(Computer com) throws Exception;
	
	/*
	 * ͨ��id��õ�����Ϣ
	 */

	public Computer get(int comId) throws Exception;

	/*
	 * ��ʾ������Ϣ
	 */
	public List<Computer> list(int pageSize, int pageNo, String keyWord) throws Exception;

	/*
	 * ���������Ϣ����
	 */
	public int getCount(String keyWord)throws Exception;


 
}