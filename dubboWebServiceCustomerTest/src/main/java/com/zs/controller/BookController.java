package com.zs.controller;


import com.zs.model.Book;
import com.zs.service.impl.BookServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/book.do")
public class BookController {
    @Resource(name="bookServiceImpl")
	private BookServiceImpl bookService;

	@RequestMapping(params = "method=add")
	public String add(Book book){
        System.out.println("BookController -- add()");
		bookService.add(book);
		return "success";
	}

	@RequestMapping(params = "method=update")
	public String update(Book book) {
		bookService.update(book);
		return "success";
	}

	public BookServiceImpl getBookService() {
		return bookService;
	}

	@Resource
	public void setBookService(BookServiceImpl bookService) {
		this.bookService = bookService;
	}
	
}
