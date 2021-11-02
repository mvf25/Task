package com.nttdata.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.demo.controller.Tasks;
import com.nttdata.demo.repository.TaskRepository;
@Service
public class TaskService {
	
	@Autowired
	TaskRepository repository;
	public int buildId(int inicio){
		
		int numero = inicio + 1; 
		return numero;
	}
	
	public boolean existeTarea(int id){
		
		//String id_cast = Integer.toString(id);
		Optional<Tasks> lib=repository.findById(id);
		
		return lib.isPresent();
	
		
		
	}
	
	public Tasks getTareaById(int id){
		
		return repository.findById(id).get();
	}

}
