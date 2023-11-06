package com.example.demo.converter;

import java.util.Optional;
import org.springframework.stereotype.Component;
import com.example.demo.dto.TodoDto;
import com.example.demo.model.Todo;

@Component
public class TodoConverter {

	public Todo dtotoentity(TodoDto tododto) {
		Todo todo = new Todo();
		todo.setId(tododto.getId());
		todo.setTask(tododto.getTask());
		todo.setStatus(tododto.isStatus());
		
		return todo;
	}

	public TodoDto entitytodto(Optional<Todo> todo) {
		TodoDto tododto = new TodoDto();
		tododto.setId(todo.get().getId());
		tododto.setTask(todo.get().getTask());
		tododto.setStatus(todo.get().isStatus());
		
		return tododto;
	}

}
