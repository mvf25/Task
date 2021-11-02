package com.nttdata.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nttdata.demo.repository.TaskRepository;

@SpringBootApplication
public class DemoApplication {
	@Autowired
	TaskRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	/*@Override
	public void run(String[] args) {
		
		//Tasks tas=repository.getById(id);
		//System.out.println(tas.getTitle());
		
	}*/


}
