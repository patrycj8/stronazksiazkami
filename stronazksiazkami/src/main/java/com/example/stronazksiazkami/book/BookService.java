package com.example.stronazksiazkami.book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();

    Book addNewBooks(Book books);

    void deleteBooks(Integer booksId, String email);

    Book updateBooks(Integer booksId, Book updatedBook);

    void deleteBookLogically(Integer bookId);
}
