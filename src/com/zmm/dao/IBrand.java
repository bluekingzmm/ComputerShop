package com.zmm.dao;

import java.sql.Connection;
import java.util.List;

 import com.zmm.dto.Brand;

public interface IBrand {
	
	
	public void setConnection(Connection con) throws Exception;

	/*
	 * ���ܣ�����Ʒ������
	 */
	public boolean addBrand(Brand b) throws Exception;

	/*
	 * ���ܣ�ɾ��Ʒ������
	 */

	public boolean deleteBrand(int id) throws Exception;

	/*
	 * ���ܣ��޸�Ʒ������
	 */

	public boolean modifyBrand(Brand b) throws Exception;

	/*
	 * ���ܣ�Ʒ���б�
	 */
	public List<Brand> listBrand() throws Exception;
	
	
	/*
	 * ���ܣ���ȡƷ��id
	 */
	public Brand getBrand(int id) throws Exception;
}
