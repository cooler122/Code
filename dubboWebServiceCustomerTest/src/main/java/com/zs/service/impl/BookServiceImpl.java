package com.zs.service.impl;

import com.zs.dao.BookDao;
import com.zs.model.Book;
import com.zs.service.BookService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component(value = "bookServiceImpl")
public class BookServiceImpl implements BookService {
    @Resource(name="bookDao")
	private BookDao bookDao;
	
	public void add(Book book){
        System.out.println("BookServiceImpl -- add()");
		bookDao.add(book);
	}
	public void update(Book book){
        System.out.println("BookServiceImpl -- update()");
		bookDao.update(book);
	}
	
}
