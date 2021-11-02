package com.nttdata.demo.controller;

import javax.persistence.*;


@Entity
@Table(name="MY_TASKS")

public class Tasks {
	

	@Id
	@Column(name="id")
	private int id;
	@Column(name="title")
	private String title;
	@Column(name="description")
	private String description;
	@Column(name="hecho")
	private boolean hecho;
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public boolean isHecho(){
		return hecho;
	}
	
	public void setHecho(boolean hecho){
		this.hecho = hecho;
	}


}
