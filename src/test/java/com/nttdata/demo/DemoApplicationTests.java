package com.nttdata.demo;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.nttdata.demo.controller.AddResponse;
import com.nttdata.demo.controller.TaskController;
import com.nttdata.demo.controller.Tasks;
import com.nttdata.demo.repository.TaskRepository;
import com.nttdata.demo.service.TaskService;



@AutoConfigureMockMvc
@SpringBootTest
class DemoApplicationTests {
	
	@Autowired
	TaskController con;
	@MockBean
	TaskRepository repository;
	@MockBean
	TaskService libraryService;
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void checkBuildIDLogic()
	{
		TaskService lib =new TaskService();
		Integer id = lib.buildId(1);
		id.equals(id+1);

		
	}
	
	@Test
	public void addBookTest()
	{
		//mock
		
		Tasks lib = buildLibrary();
		when(libraryService.buildId(5));
		when(libraryService.existeTarea(lib.getId())).thenReturn(false);
		when(repository.save(any())).thenReturn(lib);
		ResponseEntity response	=con.addTask(buildLibrary());//step
		System.out.println(response.getStatusCode());
		assertEquals(response.getStatusCode(),HttpStatus.CREATED);
		AddResponse ad= (AddResponse) response.getBody();
		ad.getId();
		assertEquals(lib.getId(),ad.getId());
		assertEquals("Tarea añadida correctamente.",ad.getMensaje());
		
		//call Mock service from code
		
	}
	
	@Test
	public void addBookControllerTest() throws Exception
	{
		Tasks lib = buildLibrary();
		ObjectMapper map =new ObjectMapper();
		String jsonString = map.writeValueAsString(lib);
		
		
		when(libraryService.buildId(6)).thenReturn(lib.getId());
		when(libraryService.existeTarea(lib.getId())).thenReturn(false);
		when(repository.save(any())).thenReturn(lib);
		
		this.mockMvc.perform(post("/addTarea").contentType(MediaType.APPLICATION_JSON)
				.content(jsonString)).andDo(print()).andExpect(status().isCreated())
		.andExpect(jsonPath("$.id").value(lib.getId()));
		
		}
	

	@Test
	public void updateBookTest() throws Exception
	{
		Tasks lib =buildLibrary();
		ObjectMapper map =new ObjectMapper();
		String jsonString = map.writeValueAsString(UpdateLibrary());
		when(libraryService.getTareaById(any())).thenReturn(buildLibrary());
	this.mockMvc.perform(put("/updateTarea/"+lib.getId()).contentType(MediaType.APPLICATION_JSON)
	.content(jsonString)).andDo(print()).andExpect(status().isOk())
	.andExpect(content().json("{\"title\":\"Boot\",\"id\":\"9\",\"description\":\"sfe\",\"hecho\":true\"}"));
	
	}
	@Test
	public void deleteBookControllerTest() throws Exception
	{
		when(libraryService.getTareaById(any())).thenReturn(buildLibrary());	
		doNothing().when(repository).delete(buildLibrary());
		this.mockMvc.perform(delete("/deleteTask").contentType(MediaType.APPLICATION_JSON)
		.content("{\"id\" : \"2\"}")).andDo(print())
		.andExpect(status().isCreated()).andExpect(content().string("Tarea borrada"));
		
		
	}
	
	
	
	
	public Tasks buildLibrary()
	{
		Tasks lib =new Tasks();
		lib.setId(322);
		lib.setTitle("Spring");
		lib.setDescription("sfe");
		lib.setHecho(false);
		return lib;
		
	}
	public Tasks UpdateLibrary()
	{
		Tasks lib = new Tasks();
		lib.setId(322);
		lib.setTitle("Boot");
		lib.setDescription("rain");
		lib.setHecho(true);
		return lib;
		
	}

}
