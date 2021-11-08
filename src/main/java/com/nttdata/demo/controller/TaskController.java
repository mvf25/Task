package com.nttdata.demo.controller;

import java.util.List;
import java.util.Optional;

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
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Hidden;



@CrossOrigin(origins = "*")
@RestController
public class TaskController {
	
	@Autowired 
	TaskRepository repository;
	
	@Autowired 
	TaskService taskService;
	
	private static final Logger logger =  (Logger) LoggerFactory.getLogger(TaskController.class);
	
	@PostMapping("/tasks")
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
	
	@GetMapping("/tasks")
	public List<Tasks> getTasks() {
		return (List<Tasks>) repository.findAll();
	}
	

	@GetMapping("/tasks/{id}")
	public Tasks getTask(@PathVariable (value = "id") int id){
		try {
			return taskService.getTareaById(id);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/")
	public String taskk(){
		return "Esto rula amigo mio.";
		
	}
	

	@PutMapping("/tasks")
	public ResponseEntity<Tasks> updateTask(@RequestBody Tasks task){

		Tasks existeTarea = taskService.getTareaById(task.getId());
		
		existeTarea.setState(task.getState());
		existeTarea.setDescription(task.getDescription());

		repository.save(existeTarea);
		
		return new ResponseEntity<Tasks>(existeTarea,HttpStatus.OK);
	}
	
	//@Hidden
	@DeleteMapping("/tasks")
	public ResponseEntity<String> deleteTask(@RequestBody Tasks task)
	{
		Tasks tarea = taskService.getTareaById(task.getId());
		repository.delete(tarea);
		
		logger.info("Tarea borrada");
		return new ResponseEntity<>("La tarea se ha borrado.",HttpStatus.CREATED);
		
		}
	
	@DeleteMapping("/tasksId")
	public ResponseEntity<String> deleteTaskId(@RequestParam int id)
	{
		Tasks tarea = taskService.getTareaById(id);
		repository.delete(tarea);
		
		logger.info("Tarea borrada por id.");
		return new ResponseEntity<>("La tarea se ha borrado.",HttpStatus.CREATED);
		
		}
	


}
