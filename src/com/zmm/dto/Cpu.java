/**
 * 
 */
package com.zmm.dto;

/**
 * @author Administrator
 *
 */
public class Cpu {

	/*
	 * cpu¿‡–Õ
	 */
	private int cpu_id;
	private String cpu_name;
	public int getCpu_id() {
		return cpu_id;
	}
	public void setCpu_id(int cpu_id) {
		this.cpu_id = cpu_id;
	}
	public String getCpu_name() {
		return cpu_name;
	}
	public void setCpu_name(String cpu_name) {
		this.cpu_name = cpu_name;
	}
	@Override
	public String toString() {
		return "Cpu [cpu_id=" + cpu_id + ", cpu_name=" + cpu_name + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cpu_id;
		result = prime * result + ((cpu_name == null) ? 0 : cpu_name.hashCode());
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
		Cpu other = (Cpu) obj;
		if (cpu_id != other.cpu_id)
			return false;
		if (cpu_name == null) {
			if (other.cpu_name != null)
				return false;
		} else if (!cpu_name.equals(other.cpu_name))
			return false;
		return true;
	}
	
}
