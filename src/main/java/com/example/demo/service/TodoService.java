package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.TodoDto;
import com.example.demo.exception.TodoNotFound;
import com.example.demo.model.Todo;

@Service
public interface TodoService {

	public String addTodo(Integer id, TodoDto todoDto);

	public String updateTodo(Integer id, Boolean status);

	public TodoDto getById(Integer id) throws TodoNotFound;

	public List<Todo> findAllTodos();

	public String deleteTodo(Integer id);
	

}


