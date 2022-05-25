package com.example.todoapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todoapp.model.Todo;
import com.example.todoapp.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	TodoService todoService;

	@GetMapping("/") // th:action="@{/}"と紐づいている
	public String home(Model model) {
		List<Todo> allTodos = todoService.searchAll();
		model.addAttribute("allTodo", allTodos);
		model.addAttribute("todo", new Todo()); // 追加：新しいTodオブジェクトを、viewに引き渡す（th:objectで受け取る）
		return "home";
	}

	// 追加（formのth:actionと紐づいている）
	@PostMapping("/")
	public String createTodo(@Valid Todo todo, BindingResult bindingResult, Model model) {
		// 入力値にエラーがあった時
		if (bindingResult.hasErrors()) {
			List<Todo> allTodo = todoService.searchAll();
			model.addAttribute("allTodo", allTodo);
			model.addAttribute("todo", todo);
			return "home";
		}

		// エラーがなければ、Todo新規作成
		todoService.addTodo(todo);
		return "redirect:/";
	}

	// update追加(未完了タスク一覧にある丸いボタンが押されたら、この処理が実行される）
	@PostMapping("/done")
	public String doneTodo(@RequestParam(name = "id") Integer todoId) {
		Todo updateTodo = todoService.findById(todoId);
		updateTodo.setDone(true);
		todoService.addTodo(updateTodo);
		return "redirect:/";
	}
}
