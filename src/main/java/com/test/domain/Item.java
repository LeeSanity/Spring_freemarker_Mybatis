package com.test.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Item {
	
	private int id;
	
	@NotEmpty
	@Length(min = 1, max = 20, message = "长度必须小于20字符")
	private String proName;
	
	@NotEmpty
	@Length(min = 0, max = 200, message = "描述字符必须小于200字符")
	private String proDesc;
	
	@NotNull
	@Pattern(regexp="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", message="手机号格式不正确")
	private String phoneNum;
	
	@NotNull
	@Email(message = "邮箱格式不正确")
	private String email;
	
	private boolean deleted = false;

	public Item(){	
	}
	public Item(String proName,String proDesc,String phoneNum,String email){
		this.proName = proName;
		this.proDesc = proDesc;
		this.phoneNum = phoneNum;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProDesc() {
		return proDesc;
	}

	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
