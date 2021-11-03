package com.nttdata.demo.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.nttdata.demo.repository.TaskRepository;
import com.nttdata.demo.service.TaskService;


import ch.qos.logback.classic.Logger;


@RestController
public class TaskController {
	
	@Autowired 
	TaskRepository repository;
	
	@Autowired 
	TaskService taskService;
	
	private static final Logger logger =  (Logger) LoggerFactory.getLogger(TaskController.class);
	
	@PostMapping("/addTask")
	public ResponseEntity addTask(@RequestBody Tasks task){

		AddResponse respuesta = new AddResponse();
		if(!taskService.existeTarea(task.getId())) {
			logger.info("No existe ninguna tarea, puede crearla.");
			task.setId(task.getId());
			repository.save(task);
			
			//HttpHeaders headers = new HttpHeaders();
			//headers.add("unique", Integer.toString((task.getId())));
			
			respuesta.setMensaje("Se ha añadido la tarea correctamente.");
			respuesta.setId(task.getId());
			
			return new ResponseEntity<AddResponse>(respuesta,HttpStatus.CREATED);
			//return new ResponseEntity<AddResponse>(respuesta,headers,HttpStatus.CREATED);
			
			
		}
		else{
			logger.info("Ya existe la tarea.");
			respuesta.setMensaje("Tarea existente");
			respuesta.setId(task.getId());
			return new ResponseEntity<AddResponse>(respuesta,HttpStatus.ACCEPTED);
		}
		
		
	}
	

	@GetMapping("/getTask/{id}")
	public Tasks getTask(@PathVariable (value = "id") int id){
		try {
			return repository.findById(id).get();
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/")
	public String taskk(){
		return "Esto rula amigo mio.";
		
	}
	
	@PutMapping("/updateTask/{id}")
	public ResponseEntity<Tasks> updateBook(@PathVariable(value="id")int id,@RequestBody Tasks task)
	{

		Tasks existeTarea = taskService.getTareaById(id);
		
		existeTarea.setTitle(task.getTitle());
		existeTarea.setDescription(task.getDescription());
		existeTarea.setHecho(task.isHecho());
		repository.save(existeTarea);//
		//
		return new ResponseEntity<Tasks>(existeTarea,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteTask")
	public ResponseEntity<String> deleteBookById(@RequestBody Tasks task)
	{
		Tasks tarea = taskService.getTareaById(task.getId());
		repository.delete(tarea);
		
		logger.info("Tarea borrada");
		return new ResponseEntity<>("La tarea se ha borrado.",HttpStatus.CREATED);
		
		}
	


}
