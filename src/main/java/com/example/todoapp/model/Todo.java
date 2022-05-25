package com.example.todoapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

//データを格納するクラス(DBへ登録、更新時の入れもの）
@Entity
@Data
@Table(name = "todolist")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 主キーの自動採番
	private Integer id;

	// バリデーション(アノテーション）追加
	@NotNull
	@NotBlank
	private String content;

	private boolean done;
}