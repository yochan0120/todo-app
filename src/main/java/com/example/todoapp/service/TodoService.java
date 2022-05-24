package com.example.todoapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todoapp.repository.Todo;
import com.example.todoapp.repository.TodoRepository;

//DBとの具体的な処理（データ取得、新規作成など）を記述するクラス
@Service
public class TodoService {

	@Autowired
	TodoRepository todoRepository;

	// todolistを全件取得
	public List<Todo> searchAll() {
		return todoRepository.findAll();
	}

	// 追加（Todoを新たにDBに登録する）
	// JpaRepositoryにはsaveメソッドがあり、引数で受け取ったエンティティを保存する
	public void addTodo(Todo todo) {
		todoRepository.save(todo);
	}
}
