package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.converter.TodoConverter;
import com.example.demo.dto.TodoDto;
import com.example.demo.exception.TodoNotFound;
import com.example.demo.model.Todo;
import com.example.demo.model.User;
import com.example.demo.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {
	@Autowired
	TodoRepository todoRepository;
	@Autowired
	TodoConverter todoConverter;
	@Autowired
	public WebClient.Builder webClientBuilder;
	@Autowired
	public RestTemplate restTemplate;

	public String addTodo(Integer userId, TodoDto todoDto) {
		User user = new User();
		user = webClientBuilder.build()
				.get().uri("http://localhost:5000/user/" + userId)
				.retrieve()
				.bodyToMono(User.class)
				.block();
//		user = restTemplate.getForObject("http://localhost:5000/user/"+userId, User.class);        VIA REST TEMPLATE
		if (user != null) {
			todoDto.setUser(user);
			todoRepository.save(todoConverter.dtotoentity(todoDto));
			return "Todo added successfully";
		} else {
			return "User with username " + userId + " not found.";
		}
	}

	public String updateTodo(Integer id, Boolean status) {
		Optional<Todo> todoOptional = Optional.of(new Todo());
		todoOptional = todoRepository.findById(id);
		if (todoOptional.isPresent()) {
			Todo todo = todoOptional.get();
			todo.setStatus(status);
			todoRepository.save(todo);
			return "Todo updated successfully";
		} else {
			return "Todo not found";
		}
	}

	public TodoDto getById(Integer id) throws TodoNotFound {
		try {
			Optional<Todo> todoOptional = Optional.of(new Todo());
			todoOptional = todoRepository.findById(id);
			TodoDto todoDto = todoConverter.entitytodto(todoOptional);
			return todoDto;
		} catch (Exception e) {
			throw new TodoNotFound("User with id " + id + " not found");
		}
	}

	public List<Todo> findAllTodos() {
		List<Todo> todos = new ArrayList<>();
		todoRepository.findAll().forEach(todos::add);
		return todos;
	}

	public String deleteTodo(Integer id) {
		Optional<Todo> todo = Optional.of(new Todo());
		todo = todoRepository.findById(id);
		if (todo.isPresent()) {
			todoRepository.deleteById(id);
			return "Todo deleted successfully";
		} else {
			return "Todo not found";
		}
	}

}
