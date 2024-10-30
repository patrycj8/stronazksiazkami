package com.example.stronazksiazkami.book;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public Book updateBooks(Integer booksId, Book updatedBook) {
        Book existingBook = booksRepository.findById(booksId)
                .orElseThrow(() -> new IllegalArgumentException("Book with id " + booksId + " does not exist"));

        if (updatedBook.getTitle() != null) {
            existingBook.setTitle(updatedBook.getTitle());
        }
        if (updatedBook.getIsbn() != null) {
            existingBook.setIsbn(updatedBook.getIsbn());
        }
        if (updatedBook.getPrice() != null) {
            existingBook.setPrice(updatedBook.getPrice());
        }
        if (updatedBook.getLanguage() != null) {
            existingBook.setLanguage(updatedBook.getLanguage());
        }
        if (updatedBook.getCategory() != null) {
            existingBook.setCategory(updatedBook.getCategory());
        }
        if (updatedBook.getPages() != null) {
            existingBook.setPages(updatedBook.getPages());
        }
        if (updatedBook.getRate() != null) {
            existingBook.setRate(updatedBook.getRate());
        }
        if (updatedBook.getFirstPublication() != null) {
            existingBook.setFirstPublication(updatedBook.getFirstPublication());
        }

        return booksRepository.save(existingBook);
    }
}