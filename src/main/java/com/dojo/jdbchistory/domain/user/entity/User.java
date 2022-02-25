package com.dojo.jdbchistory.domain.user.entity;

import java.sql.Date;

public class User {

	private long userId;
	private String userName;
	private Date birthday;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getBirthDay() {
		return birthday;
	}

	public void setBirthDay(Date birthday) {
		this.birthday = birthday;
	}

}
