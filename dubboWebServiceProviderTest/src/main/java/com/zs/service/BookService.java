package com.zs.service;

import com.zs.model.Book;

public interface BookService {
    public Book select(Book book);

    public void add(Book book);

    public void update(Book book);
}
