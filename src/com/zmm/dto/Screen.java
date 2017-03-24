/**
 * 
 */
package com.zmm.dto;

/**
 * @author Administrator
 *ÆÁÄ»³ß´ç
 */
public class Screen {

	private int screen_id;
	private String screen_name;
	public int getScreen_id() {
		return screen_id;
	}
	public void setScreen_id(int screen_id) {
		this.screen_id = screen_id;
	}
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + screen_id;
		result = prime * result + ((screen_name == null) ? 0 : screen_name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Screen other = (Screen) obj;
		if (screen_id != other.screen_id)
			return false;
		if (screen_name == null) {
			if (other.screen_name != null)
				return false;
		} else if (!screen_name.equals(other.screen_name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Screen [screen_id=" + screen_id + ", screen_name=" + screen_name + "]";
	}
	
}
