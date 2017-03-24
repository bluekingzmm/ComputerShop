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
	 * ���ܣ�����Ա��¼
	 */

	public Admin login(String adminName, String adminPassword) throws Exception;

	/*
	 * ��ȡ������Ʒ�ĸ���
	 */
	public int getComputerCount() throws Exception;
	/*
	 * ��ȡ����Ա�ĸ���
	 */
	public int getCount(Admin ad) throws Exception;

	/*
	 * �ڻ���վ��ɾ������Ա
	 */

	public boolean delete(int id) throws Exception;

	/*
	 * ��ӹ���Ա
	 */

	public boolean add(Admin ad) throws Exception;

	/*
	 * �ҵ�����Աid
	 */

	public Admin getById(int id) throws Exception;

	/*
	 * �޸Ĺ���Ա��Ϣ
	 */
	public boolean update(Admin ad) throws Exception;

	/*
	 * ��ʾ���й���Ա
	 */
	public List<Admin> listAdmin(int pageNo,int pageSize) throws Exception;

	/*
	 * ɾ������Ա������վ
	 */

	public boolean updateStatus(Admin ad) throws Exception;

	/*
	 * �鿴����վ
	 */

	public List<Admin> recycleBinList(int pageNo,int pageSize) throws Exception;

	/*
	 * ��ջ���վ
	 */

	public boolean deleteStatus() throws Exception;

	
	
	
	/*
	 * �޸�����õ�������
	 */
	
	public Admin getPassword(String adminPassword,String adminName)throws Exception;
	
}
