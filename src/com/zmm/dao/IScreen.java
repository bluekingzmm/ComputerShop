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
	 * 功能：增加屏幕尺寸名称
	 */
	public boolean addScreen(Screen s) throws Exception;

	/*
	 * 功能：删除屏幕尺寸名称
	 */

	public boolean deleteScreen(int id) throws Exception;

	/*
	 * 功能：修改屏幕尺寸名称
	 */

	public boolean modifyScreen(Screen s) throws Exception;

	/*
	 * 功能：屏幕尺寸列表
	 */
	public List<Screen> listScreen() throws Exception;
}
