/**
 * 
 */
package com.zmm.dao;

import java.sql.Connection;
import java.util.List;

import com.zmm.dto.Screen;

/**
 * @author Administrator
 *
 */
public interface IScreen {
	public Screen getScreen(int id) throws Exception;
	
	
	public void setConnection(Connection con) throws Exception;
	/*
	 * ���ܣ�������Ļ�ߴ�����
	 */
	public boolean addScreen(Screen s) throws Exception;

	/*
	 * ���ܣ�ɾ����Ļ�ߴ�����
	 */

	public boolean deleteScreen(int id) throws Exception;

	/*
	 * ���ܣ��޸���Ļ�ߴ�����
	 */

	public boolean modifyScreen(Screen s) throws Exception;

	/*
	 * ���ܣ���Ļ�ߴ��б�
	 */
	public List<Screen> listScreen() throws Exception;
}
