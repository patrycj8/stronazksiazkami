package com.example.stronazksiazkami.book.service;

import com.example.stronazksiazkami.book.model.Book;
import com.example.stronazksiazkami.book.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class BookServiceImpl implements BookService {
    private final BookRepository booksRepository;

    @Autowired
    public BookServiceImpl(BookRepository booksReposiory) {
        this.booksRepository = booksReposiory;
    }
    public List<Book> getBooks()
    {
        return booksRepository.findAll();
    }

    public Book addNewBooks(Book books) {
        Optional<Book> booksOptional = booksRepository.findBooksByTitle(books.getTitle());
        if (booksOptional.isPresent()) {
            throw new IllegalArgumentException("title exists");
        }
        Book savedBook = booksRepository.save(books);
        return savedBook;
    }

    public void deleteBooks(Integer booksId) {
        boolean exists = booksRepository.existsById(booksId);
        if (!exists)
        {
            throw new IllegalArgumentException("books with id " + booksId + " does not exist");

        }
        booksRepository.deleteById(booksId);
    }
    @Transactional
    public void updateBooks(Integer booksId, String title) {
        Book books = booksRepository.findById(booksId)
                .orElseThrow(() -> new IllegalArgumentException("Book with id " + booksId + " does not exist"));

        if (title != null && title.length() > 0 && !Objects.equals(books.getTitle(), title))
        {
            books.setTitle(title);
        }
    }
}