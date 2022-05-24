package com.example.todoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.todoapp.repository.Todo;
import com.example.todoapp.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	TodoService todoService;

	@GetMapping("/")
	public String home(Model model) {
		List<Todo> allTodos = todoService.searchAll();
		model.addAttribute("allTodo", allTodos);
		return "home";
	}

}
