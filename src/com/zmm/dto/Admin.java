package com.zmm.dto;

public class Admin {

	/*
	 * ����Ա
	 */
	private int id;
	private String admin_name;
	private String admin_password;
	private int status;



	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", admin_name=" + admin_name + ", admin_password=" + admin_password + ", status="
				+ status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admin_name == null) ? 0 : admin_name.hashCode());
		result = prime * result + ((admin_password == null) ? 0 : admin_password.hashCode());
		result = prime * result + id;
		result = prime * result + status;
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
		Admin other = (Admin) obj;
		if (admin_name == null) {
			if (other.admin_name != null)
				return false;
		} else if (!admin_name.equals(other.admin_name))
			return false;
		if (admin_password == null) {
			if (other.admin_password != null)
				return false;
		} else if (!admin_password.equals(other.admin_password))
			return false;
		if (id != other.id)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	

	// Ԫ�ؿ����ظ�������Ԫ�����򣬵�Ԫ�ز����ظ���
	// ��ô�������һ���Ƚ����ص������ˣ�Ҫ�뱣֤Ԫ�ز��ظ���������Ԫ���Ƿ��ظ�Ӧ������ʲô���ж��أ�
	// �����Object.equals�����ˡ����ǣ����ÿ����һ��Ԫ�ؾͼ��һ�Σ���ô��Ԫ�غܶ�ʱ������ӵ������е�Ԫ�رȽϵĴ����ͷǳ����ˡ�
	// Ҳ����˵����������������Ѿ���1000��Ԫ�أ���ô��1001��Ԫ�ؼ��뼯��ʱ������Ҫ����1000��equals����������Ȼ���󽵵�Ч�ʡ�
	// ���ǣ�Java�����˹�ϣ���ԭ����ϣ��Hash��ʵ�����Ǹ����������������һ��ϣ�㷨�ĸ�����Ծ����������������ˡ�
	// ��ϣ�㷨Ҳ��Ϊɢ���㷨���ǽ��������ض��㷨ֱ��ָ����һ����ַ�ϡ������ϸ�����ϣ�㷨������Ҫ���������ƪ������������Ͳ������ˡ�
	// ��ѧ�߿���������⣬hashCode����ʵ���Ϸ��صľ��Ƕ���洢�������ַ��ʵ�ʿ��ܲ����ǣ���
	// ����һ����������Ҫ����µ�Ԫ��ʱ���ȵ������Ԫ�ص�hashCode��������һ�����ܶ�λ����Ӧ�÷��õ�����λ���ϡ�
	// ������λ����û��Ԫ�أ����Ϳ���ֱ�Ӵ洢�����λ���ϣ������ٽ����καȽ��ˣ�������λ�����Ѿ���Ԫ���ˣ�
	// �͵�������equals��������Ԫ�ؽ��бȽϣ���ͬ�Ļ��Ͳ����ˣ�����ͬ��ɢ�������ĵ�ַ��
	// �����������һ����ͻ��������⡣����һ��ʵ�ʵ���equals�����Ĵ����ʹ�󽵵��ˣ�����ֻ��Ҫһ���Ρ�
}
