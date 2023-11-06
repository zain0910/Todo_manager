package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TodoDto;
import com.example.demo.exception.TodoNotFound;
import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import com.example.demo.service.TodoService;
import com.example.demo.service.TodoServiceImpl;

@RestController
@RequestMapping("/todo")
public class TodoController {

	@Autowired
	TodoRepository todoRepository;
	@Autowired
	TodoService todoService;

	@GetMapping("/todos")
	public List<Todo> findAllTodos() {
		return todoService.findAllTodos();
	}

	@GetMapping("/{id}")
	public Optional<TodoDto> getById(@PathVariable Integer id) throws TodoNotFound{
		TodoDto todoDto = todoService.getById(id);
		return Optional.ofNullable(todoDto);
	}

	@PostMapping("/add/todo/{userId}")
	public ResponseEntity<String> addUser(@PathVariable("userId") Integer userId, @RequestBody TodoDto todoDto) {
		String msg = todoService.addTodo(userId, todoDto);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}
	
//	@PostMapping("/add/claim/{customerid}/{policyid}")
//	public ResponseEntity<String> addclaim(@PathVariable("customerid") Integer customerid,
//			@PathVariable("policyid") Integer policyid, @RequestBody ClaimDto claimdto)
//			throws AlreadyClaimed, NotFound {
//		cs.addclaim(customerid, policyid, claimdto);
//		return new ResponseEntity<String>("Claim added successfully", HttpStatus.CREATED);
//	}

	@PutMapping("/update/{id}/{status}")
	public ResponseEntity<String> updateTodo(@PathVariable Integer id, @PathVariable Boolean status) {
		String msg = todoService.updateTodo(id, status);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable Integer id) {
		String msg = todoService.deleteTodo(id);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
