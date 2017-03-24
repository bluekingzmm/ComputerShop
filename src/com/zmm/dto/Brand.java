/**
 * 
 */
package com.zmm.dto;

/**
 * @author Administrator
 *品牌
 */
public class Brand {

	private int brand_id;//品牌id
	private String brand_name;//品牌名
	
	
	public int getBrand_id() {
		return brand_id;
	}


	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}


	public String getBrand_name() {
		return brand_name;
	}


	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}


	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + brand_id;
		result = prime * result + ((brand_name == null) ? 0 : brand_name.hashCode());
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
		Brand other = (Brand) obj;
		if (brand_id != other.brand_id)
			return false;
		if (brand_name == null) {
			if (other.brand_name != null)
				return false;
		} else if (!brand_name.equals(other.brand_name))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Brand [brand_id=" + brand_id + ", brand_name=" + brand_name + "]";
	}
	
}
