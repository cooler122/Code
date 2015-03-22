package com.zs.dao;


import com.zs.model.Book;
import org.springframework.stereotype.Component;

@Component(value="bookDao")
public class BookDao {
	public void add(Book book){
		System.out.print("BookDao -- add()");
	}
	public void update(Book book){
		System.out.print("BookDao -- update()");
	}
}
