package com.zs.service.impl;


import com.zs.dao.BookDao;
import com.zs.model.Book;
import com.zs.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
	private BookDao bookDao;

//    public BookDao getBookDao() {
//        return bookDao;
//    }
//
//    public void setBookDao(BookDao bookDao) {
//        this.bookDao = bookDao;
//    }

    public Book select(Book book){
        System.out.println("BookServiceImpl -- select()");
        bookDao = new BookDao();
        return bookDao.select(book);
    }

	public void add(Book book){
        System.out.println("BookServiceImpl -- add()");
		bookDao.add(book);
	}
	public void update(Book book){
        System.out.println("BookServiceImpl -- update()");
		bookDao.update(book);
	}
	
}
