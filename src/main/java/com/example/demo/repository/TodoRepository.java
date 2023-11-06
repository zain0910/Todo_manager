package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Todo;
import com.example.demo.model.User;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

//	List<Todo> findByuserName(String userName);


}
