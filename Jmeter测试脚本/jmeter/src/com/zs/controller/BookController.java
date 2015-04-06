package com.zs.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.model.Book;
import com.zs.service.BookService;

@Controller
@RequestMapping("/book.do")
public class BookController {
	@Resource(name = "bookServiceImpl")
	private BookService bookService;

	@RequestMapping(params = "method=add")
	public String add(Book book) {
		System.out.println("BookController -- add()");
		bookService.add(book);
		return "success";
	}

	@RequestMapping(params = "method=update")
	public String update(Book book) {
		bookService.update(book);
		return "success";
	}

	public BookService getBookService() {
		return bookService;
	}

	@Resource
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

}
