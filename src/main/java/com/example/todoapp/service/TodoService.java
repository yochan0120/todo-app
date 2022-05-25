package com.example.todoapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todoapp.model.Todo;
import com.example.todoapp.repository.TodoRepository;

// DBとの具体的な処理（データ取得、新規作成など）を記述するクラス
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

	// update追加(idに応じたTodoを返却する）
	public Todo findById(Integer id) {
		Optional<Todo> updateTodo = todoRepository.findById(id);
		return updateTodo.get();
	}

	// 追加（Todoの全件削除）
	public void deleteAllTodo() {
		List<Todo> allTodo = todoRepository.findAll();
		List<Todo> doneList = new ArrayList<>();
		// doneがtrueかどうかを判定。trueのものをdoneListに追加する。
		for (Todo todo : allTodo) {
			if (todo.getDone()) {
				doneList.add(todo);
			}
		}
		todoRepository.deleteAll(doneList);
	}
}