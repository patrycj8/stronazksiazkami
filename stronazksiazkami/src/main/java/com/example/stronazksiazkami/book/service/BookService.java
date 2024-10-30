package com.example.stronazksiazkami.book.service;

import com.example.stronazksiazkami.book.model.Book;

import java.util.List;

public interface BookService
{
    List<Book> getBooks();

    Book addNewBooks(Book books);
    void deleteBooks(Integer booksId);
    void updateBooks(Integer booksId, String title);
}
