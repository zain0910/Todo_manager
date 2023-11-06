package com.example.demo.dto;

import com.example.demo.model.User;

public class TodoDto {

	private Integer id;
	private String task;
	private boolean status;
	private User user;

	public TodoDto(Integer id, String task, boolean status, User user) {
		super();
		this.id = id;
		this.task = task;
		this.status = false;
		this.user = user;
	}

	public TodoDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
