package com.nttdata.demo.controller;

import javax.persistence.*;


@Entity
@Table(name="MY_TASKS")

public class Tasks {
	

	@Id
	@Column(name="id")
	private int id;
	@Column(name="state")
	private String state;
	@Column(name="description")
	private String description;

	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getState(){
		return state;
	}
	
	public void setState(String state){
		this.state = state;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	



}
