package com.zs.dao;


import com.zs.model.Book;
import org.springframework.stereotype.Component;

@Component("bookDao")
public class BookDao {
    public Book select(Book book){
        System.out.print("BookDao -- select()");
        int id = book.getId();
        if(id == 1){
            book.setAuthor("jim");
            book.setName("c# develop");
        }else if(id == 2) {
            book.setAuthor("dave");
            book.setName("java develop");
        }
        return book;
    }
	public void add(Book book){
		System.out.print("BookDao -- add()");
	}
	public void update(Book book){
		System.out.print("BookDao -- update()");
	}
}
